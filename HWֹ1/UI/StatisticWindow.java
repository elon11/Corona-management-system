/*
 * elon ifrah 207229931
 * yossi iluz 208510248
 *The class describes the statistics window
 */

package UI;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Io.*;
import Country.*;
import Simulation.*;


public class StatisticWindow extends JDialog 
{
	/**
	 * this class represent the statistic window dialog
	 */
	
	
	private Map map=null;
	private static StatisticTable tableModel;
	
	
	public StatisticWindow(JFrame window, Map map,String row_name) 
	{
		/**
		 * Constructor
		 * @param window - parent window
	
		 * @param row_name - the name of the settlement to show statistics if clicked from map
		 */
		
        super(window,"Statistics",false);
        setBounds(390,170,200,300);
        getContentPane().setLayout(new GridLayout(2, 1));
        
        JPanel bottom_panel=new JPanel(new GridLayout(1, 3));
    	bottom_panel.setLayout(new GridLayout(1, 3));
    	
    	
    	
    	Settlement[] settlements = map.getSettlement();
    	tableModel = new StatisticTable(settlements,row_name);
    
        
       
        
        //Save Button
    	JButton bt_save = new JButton("Save");
    	bt_save.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) 
    		{
    			StatisticsFile.writeCsv(map,MainClass.loadFileFunc());
    		}
    		});

    	//Add Sick Button
    	JButton bt_sick = new JButton("Add Sick");
    	bt_sick.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) 
    		{
    			try 
    			{
    				tableModel.setSick();

    			} 
    			catch (Exception e1) {
    				e1.printStackTrace();
    			}
    		}
    		});
    	
    	
    	//Douses Button
    	JButton bt_vaccinate= new JButton("Vaccinate");
    	SpinnerModel vaccinate_nodel=new SpinnerNumberModel(1,1,100,1);
    	JSpinner spinner = new JSpinner(vaccinate_nodel);
    	JPanel p_douses=new JPanel();
    	JButton bt_dose = new JButton("Add");
    	JLabel l_dose = new JLabel("Douses:");
    	p_douses.add(l_dose);
    	p_douses.add(spinner);
    	p_douses.add(bt_dose);	
    	bt_dose.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{

    			int douses = (Integer) spinner.getValue();
    			tableModel.setDouse(douses);

    		}
    	});
    	JDialog vaccinate=new JDialog(this,"Add vaccinate douses",true);
    	vaccinate.setBounds(390,170,200,300);
    	vaccinate.getContentPane().add(p_douses);
    	vaccinate.pack();
    	//Vaccinate Button
    	bt_vaccinate.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			vaccinate.setVisible(true);
    		}
    	});
    	
    	
    	bottom_panel.add(bt_save);
    	bottom_panel.add(bt_sick);
    	bottom_panel.add(bt_vaccinate);
    	getContentPane().add(tableModel);
    	getContentPane().add(bottom_panel);
    	
    	
    	pack();
	}
	public JTable getTableFromDialog()
    {
		/**
		 * @return  statistic table
		 */
    	return tableModel.getTableFromPanel();
    }
	public static void update_statistics()
	{
		/**
		 * update statistic table
		 */
		if(tableModel != null)
		{
			tableModel.updateModel();
		}
		
	}

}
