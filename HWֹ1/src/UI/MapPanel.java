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
import java.util.Iterator;

import Country.Map;
import Country.Settlement;

public class MapPanel extends JPanel {

	private Map map = null;
	private int max_x = 0;
	private int max_y = 0;

	public MapPanel() {

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

		double dimension_x = (double) this.getWidth() / max_x;
		double dimension_y = (double) this.getHeight() / max_y;

		Iterator<Settlement> iterator = map.iterator();
		while (iterator.hasNext()) {
			Settlement setl = iterator.next();
			if (setl != null) {
				g.setColor(Color.BLACK);
				for (int j = 0; j < setl.getNumN(); j++) {
					int center_x1 = findCenterX(setl);
					int center_y1 = findCenterY(setl);
					int center_x2 = setl.getneighbors()[j].getlocation().GetPoint().GetX()
							+ setl.getneighbors()[j].getlocation().GetSize().Getwidth() / 2;
					int center_y2 = setl.getneighbors()[j].getlocation().GetPoint().GetY()
							+ setl.getneighbors()[j].getlocation().GetSize().Getheight() / 2;
					NeighborDecorator n = new NeighborDecorator(setl, setl.getneighbors()[j]);
					n.SetColor(g);
					g.drawLine((int) (center_x1 * dimension_x), (int) (center_y1 * dimension_y),
							(int) (center_x2 * dimension_x), (int) (center_y2 * dimension_y));
				}
			}
		}
		for (Settlement setl : map) {
			g.setColor(Color.BLACK);
			int x = setl.getlocation().GetPoint().GetX();
			int y = setl.getlocation().GetPoint().GetY();
			int height = setl.getlocation().GetSize().Getheight();
			int width = setl.getlocation().GetSize().Getwidth();
			g.drawRect((int) (x * dimension_x), (int) (y * dimension_y), (int) (width * dimension_x),
					(int) (height * dimension_y));
			g.setColor(setl.getColor().getcolor());
			g.fillRect((int) (x * dimension_x), (int) (y * dimension_y), (int) (width * dimension_x),
					(int) (height * dimension_y));
			g.setColor(Color.BLACK);
			g.drawString(setl.getName(), (int) (x * dimension_x), (int) ((y + 15) * dimension_y));
			g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (14 * dimension_x)));
		}
	}

	public int findCenterY(Settlement s) {
		/**
		 * @param y index of the settlement
		 * @return the y center cordinate of the settlement
		 */
		return s.getlocation().GetPoint().GetY() + s.getlocation().GetSize().Getheight() / 2;
	}

	public int findCenterX(Settlement s) {
		/**
		 * @param x index of the settlement
		 * @return the x center cordinate of the settlement
		 */
		return s.getlocation().GetPoint().GetX() + s.getlocation().GetSize().Getheight() / 2;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1200, 400);
	}

	public void set_map(Map map) {
		/**
		 * Create the map after uploading the file
		 * 
		 * @param map the map
		 */
		this.map = map;
		Iterator<Settlement> iterator = map.iterator();
		iterator = map.iterator();
		max_x = max_y = 0;
		if (map != null)

			while (iterator.hasNext() && iterator.next() != null) {
				Settlement setl = iterator.next();
				if (setl.getlocation().GetPoint().GetX() + setl.getlocation().GetSize().Getwidth() > max_x) {
					max_x = setl.getlocation().GetPoint().GetX() + setl.getlocation().GetSize().Getwidth();
				}
				if (setl.getlocation().GetPoint().GetY() + setl.getlocation().GetSize().Getheight() > max_y) {
					max_y = setl.getlocation().GetPoint().GetY() + setl.getlocation().GetSize().Getheight();
				}
			}
		max_x += 10;
		max_y += 10;
		this.repaint();
	}

}
