/*
 * elon ifrah 207229931
 * yossi iluz 208510248
 *The class describes the main window in the system
 */
package UI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.FileHandler;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.Iterator;
import Country.*;
import Io.SimulationFile;
import Io.StatisticsFile;
import Simulation.*;
import Virus.*;



	public class MainWindows extends JFrame {
		private Map map=null; 
		private int sleep_time=2000; 
		private MapPanel map_panel ;
		
		public MainWindows() throws IOException 
		{
			/**
			 * Constructor
			 */
			
			super("Main Window");
			getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
			BorderLayout myBorderLayout = new BorderLayout();
			getContentPane().setLayout(myBorderLayout);
			setBounds(400,180,220,330);
			setPreferredSize(new Dimension(600, 500));
			
			
			menuBar();
			getContentPane().add(map_panel(), BorderLayout.CENTER);
			simulationSpeedSlider();

			
			this.pack();
			this.setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		public Map getmap()
		{
			/**
			 * getting map 
			 * @return the map after charging
			 */
			
				
			return map;
		}
		public int getsleeptime()
		{
			/**
			 * Stop time after each simulation
			 * @return sleep time
			 */
			return sleep_time;
		}
		public void simulationSpeedSlider()
		{
			/**
			 * Create a speed bar
			 */
	
			JPanel simulationspeed_p=new JPanel();
			simulationspeed_p.setLayout(new BoxLayout(simulationspeed_p, BoxLayout.LINE_AXIS));
			
			JSlider simulation_speed=new JSlider();
			simulation_speed.setMajorTickSpacing(5);
			simulation_speed.setMinorTickSpacing(1);
			simulation_speed.setMaximum(50);
			simulation_speed.setValue(30);
			simulation_speed.setToolTipText("<= go faster || go slower =>");
			simulation_speed.setPaintLabels(true);
			simulation_speed.setPaintTicks(true);
			
			
			JButton btspeed=new JButton("Set");
			btspeed.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					sleep_time=1000*simulation_speed.getValue();
				}
				});
			
	
			
			simulationspeed_p.add(simulation_speed);
			simulationspeed_p.add(btspeed);
			
			getContentPane().add(simulationspeed_p,BorderLayout.SOUTH);	
		}
		public MapPanel map_panel()
		{
			/**
			 *Function for creating the map window after uploading the file
			 */
			
			
		    map_panel=new MapPanel();
			map_panel.setVisible(true);
			map_panel.repaint();
			
			//Open a statistical window by clicking on a place
			map_panel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					Iterator<Settlement> iterator=map.iterator();
					while(iterator.hasNext()) 
					{
						Settlement setl=iterator.next();
						if(setl!=null)
						{
							int x=setl.getlocation().GetPoint().GetX();
							int y=setl.getlocation().GetPoint().GetY();
							int h=setl.getlocation().GetSize().Getheight();
							int w_settl=setl.getlocation().GetSize().Getwidth();
							
							if(x<=e.getPoint().getX() && e.getPoint().getX()<=x+w_settl && y<=e.getPoint().getY() && e.getPoint().getY()<=y+h)
							{
								System.out.println(setl.getName());
								StatisticWindow statistic_d =  statistic_Window(map,setl.getName());
								statistic_d.setVisible(true);
								break;
							}
						}
					}
				}
			});
			
			return map_panel;
		}
		public StatisticWindow statistic_Window(Map map,String row_name)
		{
			/**
			 * Function for creating a statistics window
			 * @param map - the map of all place
			 * @param row name -  the name of the settlement in the statistic table
			 * @return statistic window
			 */
			StatisticWindow statistic_d = new StatisticWindow(this,map,row_name);
			return statistic_d;
			
		}
		public void menuBar() throws IOException
		{
			/**
			 * the function create the menu bar on the main window
			 */
			
			
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			
		
			JMenu file = new JMenu("File");
		
			
			
			JMenuItem bt_statistics = new JMenuItem("Statistics");
			JMenuItem bt_load = new JMenuItem("Load");
			JMenuItem bt_play = new JMenuItem("Play");
			JMenuItem bt_pause = new JMenuItem("Pause");
			JMenuItem bt_stop = new JMenuItem("Stop");
			JMenuItem bt_load_log = new JMenuItem("Load log file");
			JMenuItem bt_back_log = new JMenuItem("back log file");
			
			//load button
			bt_load.setEnabled(true);
			
			bt_load.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					/**
					 * Upload Step: Get the location of the upload file and load the entire map.
					 */
					bt_load.setEnabled(false);
					bt_play.setEnabled(true);
					bt_statistics.setEnabled(true);
					
					
					File file=MainClass.loadFileFunc();
					
					SimulationFile simulationFile=new SimulationFile(file);
					try {
						
						
						map=simulationFile.FillCountryFromFile();
						Iterator<Settlement> iterator=map.iterator();
						while(iterator.hasNext()) 
						{
							Settlement setl=iterator.next();
							if(setl!=null)
							{
								setl.set_map(map);
							}
							
						}
						
						map_panel.set_map(map);
	
						map.cyclic = new CyclicBarrier(map.getsize(), new Runnable() {
							public void run() {
								
								Clock.nextTick();
								updateAll();
								try {
									Thread.sleep(sleep_time);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
						});
					
					}catch (Exception e1) {
						 
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					

				}
			});
			
			
			//statistics button
			bt_statistics.setSelected(true);
			bt_statistics.setEnabled(false);
			bt_statistics.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					StatisticWindow statistic_d=statistic_Window(map," ");
					statistic_d.setVisible(true);
					

				}
			});
			
			
			//Edit Mutations button
			JMenuItem bt_edit_mutations = new JMenuItem("Edit Mutations");
	    	IVirus[] variants={new BritishVariant(),new ChineseVariant(),new SouthAfricanVariant() };
	    	MutashionWindows edit_mutations_d = new MutashionWindows(this,variants);
	    	
	    	bt_edit_mutations.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					edit_mutations_d.setVisible(true);
				}
			});

	    	bt_load_log.setSelected(true);
	    	bt_load_log.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					if(StatisticsFile.index>0)
						StatisticsFile.fh.close();
					StatisticsFile.loadFileFunc();
				}
			});
	    	
	    	bt_back_log.setSelected(true);
	    	bt_back_log.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					if(StatisticsFile.index>0)
					{
						StatisticsFile.fh.close();
						StatisticsFile.index--;
						StatisticsFile.memento=StatisticsFile.caretaker.getMemento(StatisticsFile.index);
						StatisticsFile.originator.setMemento(StatisticsFile.memento);
						try {
							StatisticsFile.fh=new FileHandler(StatisticsFile.originator.getState());
						} catch (SecurityException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
	    	
	    	
	    	
	    	
	    	
	    	
	    	//Exit button
			JMenuItem bt_exit = new JMenuItem("Exit");
			bt_exit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					System.exit(0);
				}
			});
			
		
			file.add(bt_load);
			file.addSeparator();
			file.add(bt_statistics);
			file.addSeparator();
			file.add(bt_edit_mutations);
			file.addSeparator();
			file.add(bt_load_log);
			file.addSeparator();
			file.add(bt_back_log);
			file.addSeparator();
			file.add(bt_exit);
			menuBar.add(file);
			
	
			JMenu bt_submenu_simulation = new JMenu("Simulation");
			
			
			//play button
			bt_play.setEnabled(false);
			bt_play.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					map.setisPaused(false);
					bt_play.setEnabled(false);
					bt_pause.setEnabled(true);
					bt_stop.setEnabled(true);
					MainClass.setPlay(true);
					MainClass.setPause(false);
					MainClass.setStop(false);
					synchronized (map) {
						map.notifyAll();
					}
				}
			});
			
			
			//pause button
			bt_pause.setEnabled(false);
			bt_pause.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					bt_pause.setEnabled(false);
					bt_play.setEnabled(true);
					bt_stop.setEnabled(true);
					map.setisPaused(true);
					MainClass.setPlay(false);
					MainClass.setPause(true);
					MainClass.setStop(false);
				}
			});
			
			
			//stop button
			bt_stop.setEnabled(false);
			bt_stop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					map.setisPaused(false);
					map.setnotStop(false);
					bt_play.setEnabled(false);
					bt_pause.setEnabled(false);
					bt_stop.setEnabled(false);
					bt_load.setEnabled(true);
					bt_statistics.setEnabled(false);
					MainClass.setPlay(false);
					MainClass.setStop(true);
					MainClass.setPause(false);
					map=null;
					map_panel.set_map(null);
					
				}
			});
			
			
			
			JMenuItem bt_set_tick = new JMenuItem("Set tick per day");
			SpinnerModel tick_per_day=new SpinnerNumberModel(1,1,100,1);
			JSpinner spinner = new JSpinner(tick_per_day);
			JPanel p_tick=new JPanel();
			JButton bt_b = new JButton("Set");
			JLabel l_tick = new JLabel("ticks:");
			p_tick.add(l_tick);
			p_tick.add(spinner);
			p_tick.add(bt_b);
			//Set button
			bt_b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					int spinner_tick = (Integer) spinner.getValue();
					Clock.set_ticks_per_day(spinner_tick);
				}
			});
			
			//Set tick per day button
			JDialog set=new JDialog(this,"Set tick per day",true);
			set.setBounds(390,170,200,300);
			set.getContentPane().add(p_tick);
			set.pack();
			bt_set_tick.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					set.setVisible(true);
				}
			});
			
			
		
			bt_submenu_simulation.add(bt_play);
			bt_submenu_simulation.addSeparator();
			bt_submenu_simulation.add(bt_pause);
			bt_submenu_simulation.addSeparator();
			bt_submenu_simulation.add(bt_stop);
			bt_submenu_simulation.addSeparator();
			bt_submenu_simulation.add(bt_set_tick);
			menuBar.add(bt_submenu_simulation);
			
			
		
			JMenu bt_submenu_help = new JMenu("Help");
			
			
			
			JMenuItem bt_help = new JMenuItem("Help");
			
			
			JDialog help_dialog=new JDialog(this,"Help",true);
			help_dialog.setBounds(390,170,200,300);
			JPanel help_p=new JPanel();
			help_p.setLayout(new BoxLayout(help_p,BoxLayout.PAGE_AXIS));
			
		
			JLabel l = new JLabel("<html>Hello<br/>"
					+ "This is a system for managing the corona virus by simulations<br/> "
					+ "The role of each simulation is to manage the people on the map according to defined places who is sick, who is healthy, who is infected with the virus and at what time.<br/>"
					+ "In the main window there is a menu for the information in the system. In addition, there is a map that shows the layout of the places with the population. You can also select the speed of the simulation.<br/>"
					+ "The colors of the places symbolize the state of morbidity in the locality<br/>"
					+ "The system allows filtering and sorting to display the information\r\n"
					+ "Also in the statistics window you can see statistical information everywhere about the spread of the virus<br/>"
					+ "Because there are developments of the virus for mutations dynamically, the mutations that are added over time can be edited in the system, and in the same way, the amount of vaccines can be updated and added anywhere as needed.<br/>"
					+ "Successfully<html>");
			
			
			help_p.add(l);
			
			//help button
			help_dialog.getContentPane().add(help_p);
			help_dialog.pack();
			bt_help.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					help_dialog.setVisible(true);
				}
			});

			
			//About button
			JMenuItem about = new JMenuItem("About");
			JDialog about_dialog=new JDialog(this,"About",true);
			about_dialog.setBounds(390,170,200,300);
			JPanel about_p=new JPanel();
			about_p.setLayout(new BoxLayout(about_p,BoxLayout.PAGE_AXIS));
			JLabel lb = new JLabel("<html>Program Name:    Corona Simulation<br/>"
					+ "Production date:                    2.5.2021<br/><br/>"
					+ "Creators: <br/>"
					+ "elon yifrah                              ID:207229931<br/>"
					+ "yossi iluz         ID:208510248<br/><html>");

			about_p.add(lb);
			about_dialog.getContentPane().add(about_p);
			about_dialog.pack();
			about.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					about_dialog.setVisible(true);
				}
			});
			
			
		
			bt_submenu_help.add(bt_help);
			bt_submenu_help.addSeparator();
			bt_submenu_help.add(about);
			
			
		
			menuBar.add(bt_submenu_help);
		}
		public void updateAll() {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					/**
					 *  update the GUI 
					 */
					map_panel.repaint();
					StatisticWindow.update_statistics();
				}
			});
		}
}
		


 
