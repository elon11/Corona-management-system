/*
 * elon ifrah 207229931
 * yossi iluz 208510248
 *The department describes the map drawing
 */
package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import Country.Map;
import Country.Settlement;

public class MapPanel extends JPanel {
	
	private Map map = null;
	private int max_x = 0;
	private int max_y = 0;
	public  MapPanel () {
		
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (map == null) {
			return;
		}
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		double dimension_x = (double)this.getWidth() / max_x;
		double dimension_y = (double)this.getHeight() / max_y;

		
		
		for (int i = 0; i < map.getSettlement().length-1; i++) {
			g.setColor(Color.BLACK);
			//for (int j = 0; j < map.getSettlement()[i].getneighbors().length; j++) {
			for (int j = 0; j < map.getSettlement()[i].getNumN(); j++) {
				int center_x1 = findCenterX(i);
				int center_y1 = findCenterY(i);
				int center_x2 = map.getSettlement()[i].getneighbors()[j].getlocation().GetPoint().GetX()
						+ map.getSettlement()[i].getneighbors()[j].getlocation().GetSize().Getwidth() / 2;
				int center_y2 = map.getSettlement()[i].getneighbors()[j].getlocation().GetPoint().GetY()
						+ map.getSettlement()[i].getneighbors()[j].getlocation().GetSize().Getheight() / 2;
				g.drawLine((int)(center_x1*dimension_x), (int)(center_y1*dimension_y), (int)(center_x2*dimension_x),(int) (center_y2*dimension_y));			
			}
		}
		for (int i = 0; i < map.getSettlement().length-1; i++) {
			g.setColor(Color.BLACK);
			int x=map.getSettlement()[i].getlocation().GetPoint().GetX();
			int y=map.getSettlement()[i].getlocation().GetPoint().GetY();
			int height=map.getSettlement()[i].getlocation().GetSize().Getheight();
			int width=map.getSettlement()[i].getlocation().GetSize().Getwidth();
			g.drawRect((int)(x*dimension_x),(int)(y*dimension_y),(int)(width*dimension_x),(int)(height*dimension_y));
			g.setColor(map.getSettlement()[i].getColor().getcolor());
			g.fillRect((int)(x*dimension_x),(int)(y*dimension_y),(int)(width*dimension_x),(int)(height*dimension_y));
			g.setColor(Color.BLACK);
			g.drawString(map.getSettlement()[i].getName(),(int)(x*dimension_x),(int)((y+15)*dimension_y));
			g.setFont(new Font("TimesRoman", Font.PLAIN,(int) (14*dimension_x))); 
		}
	}
		
	
	
	public int findCenterY(int y)
	{
		/**
		 * @param y index of the settlement
		 * @return the y center cordinate of the settlement
		 */
		return map.getSettlement()[y].getlocation().GetPoint().GetY()+map.getSettlement()[y].getlocation().GetSize().Getheight()/2;
	}
	
	public int findCenterX(int x)
	{
		/**
		 * @param x index of the settlement
		 * @return the x center cordinate of the settlement
		 */
		return map.getSettlement()[x].getlocation().GetPoint().GetX()+map.getSettlement()[x].getlocation().GetSize().Getheight()/2;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1200,400);
	}
	
	public void set_map(Map map) {
		/**
		 * Create the map after uploading the file
		 * @param map the map
		 */
		this.map = map;
		
		max_x = max_y = 0;
		if (map != null)
			for (int i = 0; i < map.getSettlement().length -1; i++) {
				if (map.getSettlement()[i].getlocation().GetPoint().GetX()+map.getSettlement()[i].getlocation().GetSize().Getwidth() > max_x) {
					max_x = map.getSettlement()[i].getlocation().GetPoint().GetX()+map.getSettlement()[i].getlocation().GetSize().Getwidth();
				}
				if (map.getSettlement()[i].getlocation().GetPoint().GetY()+map.getSettlement()[i].getlocation().GetSize().Getheight() > max_y) {
					max_y = map.getSettlement()[i].getlocation().GetPoint().GetY()+map.getSettlement()[i].getlocation().GetSize().Getheight();
				}
			}
		max_x += 10;
		max_y +=10;
		this.repaint();
	}
		

}
