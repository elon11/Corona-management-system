/*
 * elon ifrah 207229931
 * yossi iluz 208510248
 *The class describes the statistics table
 */
package UI;


import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import Country.*;
import Population.*;
import Virus.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class StatisticTable extends JPanel implements ActionListener
{
	public enum ColumnName 
	{
		/**
		 * the name of columns
		 */
		ZERO ("Settlement Name", 0),
		ONE("Settlement Type",1),
		TWO("Population", 2),
		THREE("Ramzor color", 3),
		FOUR("Sick Percentages", 4),
		FIVE("Vaccine doses", 5),
		SIX("dead", 6);
		
		
	    private final int col; 
	    private final String colname; 
	    
	    ColumnName(String name, int col) {
	    	/**
	    	 * constractor
	    	 * @param name - column name
	    	 * @param col - the col number
	    	 */
	        this.col=col;
	        this.colname = name;
	    }
		public int getcol() {
			return col;
		}
		
        @Override
        public String toString() 
        {
            return colname;
        }
		
	}
    public class StatisticModel extends AbstractTableModel 
    {
    	/**
    	 * The class implements the table interface
    	 */
    	
    	
    	private static final double initialContagion = 0.01;
        private Settlement[] data;
        private final String[] columnNames = {"Settlement Name","Settlement Type","Population","Ramzor color","Sick Percentages","Vaccine doses","dead"};   ;

        
        public StatisticModel(Settlement[] data) 
        {
        	/**
        	 * Constructor
        	 * @param data - all the settlements in the map
        	 */
            this.data = data;
        }

        @Override
        public int getRowCount() 
        {
        	/**
        	 * getting the number of rows
        	 * @return the number of rows 
        	 */
            return data.length-1;
        }

        @Override
        public int getColumnCount() 
        {
         	/**
        	 * getting the number of columns
        	 * @return the number of columns
        	 */
            return 7;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) 
        {
        	
        	/**
        	 * get the value at cell by row and col index
        	 * @param rowIndex - row index
        	 * @param columnIndex - col index
        	 * return the value at the cell 
        	 */
        	Settlement settlement = data[rowIndex];
            switch (columnIndex) {
                case 0: return settlement.getName();
                case 1:
                	if(settlement instanceof City)
    	    			return "City";
    	    		else if(settlement instanceof Kibbutz)
    	    			return "Kibbutz";
    	    		else
    	    			return "Moshav";
             
                case 2: return settlement.getPopulation();
                case 3: return settlement.getColor();
                case 4: return settlement.ContagiousPercent()*100+"%";
                case 5: return settlement.getvaccine_doses();
                case 6: return settlement.getnum_of_dead();
            }
            return null;
        }

        @Override
        public String getColumnName(int column) 
        {
        	/**
        	 * getting column name
        	 * @param column - the number of column
        	 * @return the name of the column
        	 */
            return columnNames[column];
        }

        @Override
        public Class getColumnClass(int c) 
        {
        	/**
        	 * @param c - column index
        	 * @returm class of the value
        	 */
            return getValueAt(0, c).getClass();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) 
        {
        	/**
        	 * @param row - row index
        	 * @param col - col index
        	 * @return if cell is editable
        	 */
            return columnIndex >= 0;
        }
        public void setSick(int row) 
        {
        	/**
        	 * set number of sick people in the settlement
        	 * @param row - the row index
        	 */
        	
        	Random rand=new Random();
        	IVirus virus=null;
        	Settlement settlement = data[row];
        	int z=rand.nextInt();
    		if(z%3==0)
    			virus=new BritishVariant();
    		else if(z%3==1)
    			virus=new ChineseVariant();
    		else
    			virus=new SouthAfricanVariant();
    		
    		double numContagion=settlement.getPopulation()*initialContagion;
    		for (int i=0;i<numContagion&&settlement.getPopulation()<settlement.getmax_people();i++)
    		{
    			
    			int x=rand.nextInt(settlement.getListhealthy().size()-1);
    			if (settlement.getListhealthy().get(x).Contagion(virus) instanceof Sick)
    			{
    				settlement.getListsick().add(settlement.getListhealthy().get(x).Contagion(virus));
        			settlement.getListhealthy().remove(x);
    			}
    		}
    		settlement.setColor(settlement.CalculateRamzorGrade());

            fireTableCellUpdated(row, 4);
            fireTableCellUpdated(row, 3);
        }
        public void setdouses(int row,int douses) 
        {
        	/**
        	 * set number of douses in settlement
        	 * @param row - row index
        	 * @param douses - number of douses to add the settlement
        	 */
        	Settlement settlement = data[row];
        	settlement.setvaccine_doses(douses);

            fireTableCellUpdated(row, 5);
        }
        @Override
		public void fireTableDataChanged() {
			/**
			 * update all data
			 */
			fireTableChanged(new TableModelEvent(this, // tableModel
					0, // firstRow
					getRowCount() - 1, // lastRow
					TableModelEvent.ALL_COLUMNS, // column
					TableModelEvent.UPDATE)); // changeType
		}
        public void updateTable()
        {
        	fireTableDataChanged();
        }
    }

    
    private TableRowSorter<StatisticModel> sorter;
    private JTextField tbFilterText;
    private int col;
    private JTable table;
    private StatisticModel model;
    private final JComboBox<ColumnName> column;
    

    public StatisticTable(Settlement[] data,String row_name) 
    {
    	/**
    	 * Constructor
    	 * @param data - all the settlements in the map
    	 * @row_name - the name of the settlement to show statistics
    	 */
    	this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    	JPanel top=new JPanel();
    	top.setLayout(new BoxLayout(top, BoxLayout.LINE_AXIS));
    	column = new JComboBox<>(ColumnName.values());
    	column.setSelectedIndex(0);
    	column.addActionListener(this);
        model = new StatisticModel(data);
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 150));
        table.setFillsViewportHeight(true);
        table.setRowSorter(sorter = new TableRowSorter<StatisticModel>(model));
        InitialFilter(row_name);
        this.add(new JLabel("filter:"));
        this.add(top);
        this.add(new JScrollPane(table));
        top.add(new JLabel("Column:"));
        top.add(column);
        top.add(new JLabel("Row:"));
        top.add(tbFilterText = new JTextField());

        
        tbFilterText.setToolTipText("Filter Row");
        column.setToolTipText("Filter Column");
        tbFilterText.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { newFilter(); }
            public void removeUpdate(DocumentEvent e) { newFilter(); }
            public void changedUpdate(DocumentEvent e) { newFilter(); }
        
        });
        
        this.setVisible(true);
    }
    public void updateModel()
    {
    	/**
    	 *update data model 
    	 */
    	model.updateTable();
    	newFilter();
    }
    public void setSick()
    {
    	/**
    	 * sick setter
    	 */
    	if(table.getSelectedRow()>=0)
    	{
        	model.setSick(table.getRowSorter().convertRowIndexToModel(table.getSelectedRow()));
    	}


    }
    public void setDouse(int douses)
    {
    	/**
    	 * douses setter
    	 * @param douses - the number of douses to add to the settlement
    	 */
    	if(table.getSelectedRow()>=0)
    		model.setdouses(table.getRowSorter().convertRowIndexToModel(table.getSelectedRow()),douses);
    }
    public void actionPerformed1(ActionEvent e) {
        col=column.getItemAt(column.getSelectedIndex()).getcol();
        newFilter();
        
    }
    public int getcol()
    {
    	/**
    	 * @return the col number
    	 */
    	return col;
    }
    public JTable getTableFromPanel()
    {
    	/**
    	 * @return statistic table
    	 */
    	return table;
    }
    
    

    
    private void newFilter() 
    {
    	/**
    	 * filtering the table by select
    	 */
        try {
            sorter.setRowFilter(RowFilter.regexFilter(tbFilterText.getText(), getcol()));
        } catch (java.util.regex.PatternSyntaxException e) {
           
        }
    }
    private void InitialFilter(String row_name) 
    {
    	/**
    	 * filter by the selected settlement
    	 */
        try {
            sorter.setRowFilter(RowFilter.regexFilter(row_name, getcol()));
        } catch (java.util.regex.PatternSyntaxException e) {
            
        }
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
