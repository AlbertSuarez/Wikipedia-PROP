package wikipedia.presentation;

import g13.*;
import javax.swing.event.EventListenerList;
import java.util.EventListener;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.view.mxGraph;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.RenderingHints;


interface GraphPanelOnItemClickListener extends EventListener {
	public void onItemClick(String item);
}

public class GraphPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	protected EventListenerList listenerList;

	private static int PANEL_W = 720;
	private static int PANEL_H = 480;
	final private static int MOVE_THRESHOLD = 50;
	final private static double ZOOM_FACTOR = 0.75;

	private mxGraph mxg;
	private mxGraphComponent mxgc;
	private BufferedImage orig_img;

	private double scaled_w;
	private double scaled_h;
	private double img_w;
	private double img_h;
	private double img_x;
	private double img_y;
	private int mouse_x;
	private int mouse_y;
	private boolean cc;
	private OGraph g;

	/**
	 * Creates a Graph Panel
	 * @param g graph
	 * @param w width
	 * @param h height
	 * @param cc indicates if will be a community collection representation or not
	 */
	public GraphPanel(OGraph g, int w, int h, boolean cc) {

		listenerList = new EventListenerList();

		PANEL_W = w;
		PANEL_H = h;

		this.g = g;
		this.cc = cc;

		refresh();

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
					setImagePosition((int)(img_x + (p_x - mouse_x)), (int)(img_y + (p_y - mouse_y)));
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

				if (new_w < 100 && new_h < 100) return;
				if (new_w > img_w*5 && new_h > img_h*5) return;

				double zoom_x = mouse_x - img_x;
				double zoom_y = mouse_y - img_y;

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

				img_x = mouse_x - zoom_x * zoom;
				img_y = mouse_y - zoom_y * zoom;

				scaled_w = new_w;
				scaled_h = new_h;

				repaint();
			}

		});
	}

	/**
	 * Set Size Image
	 * @param w new width
	 * @param h new height
	 */
	public void setSizeImage(int w, int h) {

		scaled_w = w;
		scaled_h = h;
		repaint();
	}

	/**
	 * Press to the graph
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	private void graphPress(int x, int y) {
		int trans_x = (int)((x - img_x) * img_w/scaled_w);
		int trans_y = (int)((y - img_y) * img_h/scaled_h);

		Object obj = mxgc.getCellAt(trans_x, trans_y);;
		if (obj != null) {
			mxCell cell = (mxCell)obj;
			fireOnItemClick(cell.getId());
		}
	}

	/**
	 * Set image position
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	private void setImagePosition(int x, int y) {
		if (x > (PANEL_W-MOVE_THRESHOLD)) return;
		if (x + scaled_w < MOVE_THRESHOLD) return;
		if (y > (PANEL_H-MOVE_THRESHOLD)) return;
		if (y + scaled_h < MOVE_THRESHOLD) return;
		img_x = x;
		img_y = y;
		repaint();
	}

	/**
	 * Get preferred size
	 */
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

		g2d.drawImage(orig_img, (int)img_x, (int)img_y, (int)scaled_w, (int)scaled_h, null);
	}

	public void refresh() {

		mxg = g.toMxGraph(cc);
		mxgc = new mxGraphComponent(mxg);

		orig_img = mxCellRenderer.createBufferedImage(mxg, null, 1, Color.WHITE, true, null);
		if (orig_img == null)
			return;

		final int orig_w = orig_img.getWidth();
		final int orig_h = orig_img.getHeight();

		img_w = (int)orig_w;
		img_h = (int)orig_h;

		if (img_w > img_h) {
			double ratio = PANEL_W/(double)img_w;
			scaled_w = ratio * img_w;
			scaled_h = ratio * img_h;

			img_x = 0;
			img_y = PANEL_H/2 - scaled_h/2;
		} else {
			double ratio = PANEL_H/(double)img_h;
			scaled_w = ratio * img_w;
			scaled_h = ratio * img_h;

			img_x = PANEL_W/2 - scaled_w/2;
			img_y = 0;
		}
		repaint();
	}

	/**
	 * Add on item listener
	 * @param listener the listener
	 */
	public void addOnItemClickListener(GraphPanelOnItemClickListener listener) {
		listenerList.add(GraphPanelOnItemClickListener.class, listener);
	}

	/**
	 * Remove on item listener
	 * @param listener the listener
	 */
	public void removeOnItemClickListener(GraphPanelOnItemClickListener listener) {
		listenerList.remove(GraphPanelOnItemClickListener.class, listener);
	}

	/**
	 * Fire on item click
	 * @param item the item
	 */
	void fireOnItemClick(String item) {
		Object[] listeners = listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i = i+2) {
			if (listeners[i] == GraphPanelOnItemClickListener.class) {
				((GraphPanelOnItemClickListener) listeners[i+1]).onItemClick(item);
			}
		}
	}
}
