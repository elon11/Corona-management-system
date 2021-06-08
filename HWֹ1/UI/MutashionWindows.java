/*
 * elon ifrah 207229931
 * yossi iluz 208510248
 *The class describes the mutation editing window
 */
package UI;

import javax.swing.JFrame;
import UI.*;
import Virus.*;

import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

public class MutashionWindows extends JDialog 
{
    private static class MutationModel extends AbstractTableModel 
    {
    	/**
    	 *  The class implements the table interface
    	 */
    	
    	
        private IVirus[] data;
        private final String[] columnNames ={"British Mutation","Chinese Mutation","SouthAfrican Mutation"};

        public MutationModel(IVirus[] data) 
        {
        	/**
        	 * Constructor
        	 * @param data - contain all the variant types
        	 */
        	data[0]=new BritishVariant();
        	data[1]=new ChineseVariant();
        	data[2]=new SouthAfricanVariant();
            this.data = data;
        }

        @Override
        public int getRowCount() 
        {
        	/**
        	 * getting the number of rows
        	 * @return the number of rows 
        	 */
            return data.length;
        }

        @Override
        public int getColumnCount() 
        {
         	/**
        	 * getting the number of columns
        	 * @return the number of columns
        	 */
            return 3;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) 
        {
        	/**
        	 * @param rowIndex - row index
        	 * @param columnIndex - col index
        	 * return the value at the cell 
        	 */
        	IVirus virus = data[rowIndex];
           if(rowIndex == 0)
           {
        	   switch (columnIndex) {
               case 0: 
            	   return BritishVariant.Get_British_mutation();
               case 1:
   	    			return BritishVariant.Get_Chinese_mutation();
               case 2: return BritishVariant.Get_SouthAfrican_mutation();
        	   }
           }
           if(rowIndex == 1)
           {
        	   switch (columnIndex) {
               case 0: 
            	   return  ChineseVariant.Get_British_mutation();
               case 1:
   	    			return  ChineseVariant.Get_Chinese_mutation();
               case 2: return  ChineseVariant.Get_SouthAfrican_mutation();
        	   }
           }
           if(rowIndex == 2)
           {
        	   switch (columnIndex) {
               case 0: 
            	   return  SouthAfricanVariant.Get_British_mutation();
               case 1:
   	    			return  SouthAfricanVariant.Get_Chinese_mutation();
               case 2: return  SouthAfricanVariant.Get_SouthAfrican_mutation();
        	   }
           }
           return null;
            
        }

        @Override
        public String getColumnName(int column) 
        {
        	/**
        	 * @param column - the number of column
        	 * @return the name of the column
        	 */
        	return columnNames[column];
        }

        @Override
        public Class getColumnClass(int column) 
        {
        	/**
        	 * @param column - column index
        	 * @returm class of the value
        	 */
        	return getValueAt(0, column).getClass(); 
        }
        @Override
        public boolean isCellEditable(int row, int col) 
        { 	
        	/**
        	 * @param row - row index
        	 * @param col-  col index
        	 * @return if cell is editable-allways true
        	 */
        	return true; 
        }   
        @Override
        public void setValueAt(Object aValue, int row, int col) 
        {
        	/**
        	 * @param avalue - new value
        	 * @param row - row index
        	 * @param col - column index
        	 */
        	boolean x=(Boolean) aValue;
            if(row == 0)
            {
            	if(col==0)
            		BritishVariant.Set_British_mutation(x);
            	if(col==1)
            		BritishVariant.Set_Chinese_mutation(x);
            	if(col==2)
            		BritishVariant.Set_SouthAfrican_mutation(x);
            }
            if(row == 1)
            {
            	if(col==0)
            		ChineseVariant.Set_British_mutation(x);
            	if(col==1)
            		ChineseVariant.Set_Chinese_mutation(x);
            	if(col==2)
            		ChineseVariant.Set_SouthAfrican_mutation(x);
            }
            if(row == 2)
            {
            	if(col==0)
            		SouthAfricanVariant.Set_British_mutation(x);
            	if(col==1)
            		SouthAfricanVariant.Set_Chinese_mutation(x);
            	if(col==2)
            		SouthAfricanVariant.Set_SouthAfrican_mutation(x);
            }
            fireTableCellUpdated(row, col);
        }
    }

    public MutashionWindows(JFrame window,IVirus[] data) 
    {
    	/**
    	 * Constructor
    	 * @param window - parent window
    	 * @param data - contain all the variant types 
    	 */
        super(window, "Edit Mutation ",true);
        MutationModel model = new MutationModel(data);
        
        
        JTable table = new JTable(model);
        String row[]= {"British Variant", "Chinese Variant", "SouthAfrican Variant"};
		RowedTableScroll jt_rowed =new RowedTableScroll(table,row);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setPreferredScrollableViewportSize(new Dimension(getPreferredSize()));
        table.setFillsViewportHeight(true);
        
        
        this.add(new RowedTableScroll(table,row));
        this.getPreferredSize();
        setBounds(390,170,200,300);
        this.pack();
    }
	@Override
	public Dimension getPreferredSize() 
	{
		/**
		 * selected according maximum of x and y points
		 * @return dimention 
		 */
		return new Dimension(700,125);
	}


}
