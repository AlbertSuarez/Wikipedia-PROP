package wikipedia.presentation;

import wikipedia.presentation.*;
import g13.*;

import javax.swing.event.EventListenerList;
import java.util.EventListener;
import java.util.EventObject;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.view.mxGraph;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;


interface GraphPanelOnItemClickListener extends EventListener {
	public void onItemClick(String item);
}

public class GraphPanel extends JPanel {

	protected EventListenerList listenerList;

	private static int PANEL_W = 720;
	private static int PANEL_H = 480;
	final private static int MOVE_THRESHOLD = 50;
	final private static double ZOOM_FACTOR = 0.75;

	private mxGraph mxg;
	private mxGraphComponent mxgc;
	private BufferedImage orig_img;

	private int scaled_w;
	private int scaled_h;
	private int img_w;
	private int img_h;
	private int img_x;
	private int img_y;
	private int mouse_x;
	private int mouse_y;

	public GraphPanel(OGraph g, int w, int h) {

		listenerList = new EventListenerList();

		PANEL_W = w;
		PANEL_H = h;

		mxg = g.toMxGraph();
		mxgc = new mxGraphComponent(mxg);

		orig_img = mxCellRenderer.createBufferedImage(mxg, null, 1, Color.WHITE, true, null);
		final int orig_w = orig_img.getWidth();
		final int orig_h = orig_img.getHeight();

		img_w = orig_w;
		img_h = orig_h;

		if (img_w > img_h) {
			double ratio = PANEL_W/(double)img_w;
			scaled_w = (int)(ratio * img_w);
			scaled_h = (int)(ratio * img_h);

			img_x = 0;
			img_y = PANEL_H/2 - scaled_h/2;
		} else {
			double ratio = PANEL_H/(double)img_h;
			scaled_w = (int)(ratio * img_w);
			scaled_h = (int)(ratio * img_h);

			img_x = PANEL_W/2 - scaled_w/2;
			img_y = 0;
		}

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					graphPress(e.getX(), e.getY());
				}
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				mouse_x = e.getX();
				mouse_y = e.getY();
			}
			public void mouseDragged(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					int p_x = e.getX();
					int p_y = e.getY();
					setImagePosition(img_x + (p_x - mouse_x), img_y + (p_y - mouse_y));
					mouse_x = p_x;
					mouse_y = p_y;
				}
			}
		});

		addMouseWheelListener(new MouseAdapter() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				double zoom = ZOOM_FACTOR;
				if (e.getWheelRotation() == -1) zoom = 1/zoom;

				double new_w = scaled_w * zoom;
				double new_h = scaled_h * zoom;

				if (new_w < 100 || new_h < 100) return;
				if (new_w > img_w*5 || new_h > img_h*5) return;

				int zoom_x = mouse_x - img_x;
				int zoom_y = mouse_y - img_y;

				if (zoom_x > scaled_w) {
					zoom_x = scaled_w;
				} else if (zoom_x < 0) {
					zoom_x = 0;
				}
				if (zoom_y > scaled_h) {
					zoom_y = scaled_h;
				} else if (zoom_y < 0) {
					zoom_y = 0;
				}

				img_x = (int)(mouse_x - zoom_x * zoom);
				img_y = (int)(mouse_y - zoom_y * zoom);

				scaled_w = (int)new_w;
				scaled_h = (int)new_h;

				repaint();
			}

		});
	}

	private void graphPress(int x, int y) {
		int trans_x = (int)((x - img_x) * (img_w/(double)scaled_w));
		int trans_y = (int)((y - img_y) * (img_h/(double)scaled_h));

		Object obj = mxgc.getCellAt(trans_x, trans_y);;
		if (obj != null) {
			mxCell cell = (mxCell)obj;
			fireOnItemClick(cell.getId());
		}
	}

	private void setImagePosition(int x, int y) {
		if (x > (PANEL_W-MOVE_THRESHOLD)) return;
		if (x + scaled_w < MOVE_THRESHOLD) return;
		if (y > (PANEL_H-MOVE_THRESHOLD)) return;
		if (y + scaled_h < MOVE_THRESHOLD) return;
		img_x = x;
		img_y = y;
		repaint();
	}


	public Dimension getPreferredSize() {
		return new Dimension(PANEL_W, PANEL_H);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
			RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		g2d.drawImage(orig_img, img_x, img_y, scaled_w, scaled_h, null);
	}

	public void addOnItemClickListener(GraphPanelOnItemClickListener listener) {
		listenerList.add(GraphPanelOnItemClickListener.class, listener);
	}
	public void removeOnItemClickListener(GraphPanelOnItemClickListener listener) {
		listenerList.remove(GraphPanelOnItemClickListener.class, listener);
	}
	void fireOnItemClick(String item) {
		Object[] listeners = listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i = i+2) {
			if (listeners[i] == GraphPanelOnItemClickListener.class) {
				((GraphPanelOnItemClickListener) listeners[i+1]).onItemClick(item);
			}
		}
	}
}
