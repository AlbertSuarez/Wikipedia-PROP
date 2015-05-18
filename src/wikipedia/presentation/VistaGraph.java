package wikipedia.presentation;

import g13.*;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.view.mxGraph;

public class VistaGraph extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public VistaGraph(final PresentationController pc) {
		super("Wikipedia");
		setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int width = (int)screenSize.getWidth();
		final int height = (int)screenSize.getHeight();
		setBounds((width/2)-450,(height/2)-300,900,600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		OGraph g = pc.getGraph();
		final mxGraph mxg = g.toMxGraph();
		final mxGraphComponent mxgc = new mxGraphComponent(mxg);

		BufferedImage orig_img = mxCellRenderer.createBufferedImage(mxg, null, 1, Color.WHITE, true, null);
		final int orig_w = orig_img.getWidth();
		final int orig_h = orig_img.getHeight();

		JLabel label;
		if (orig_w > width || orig_h > height) {
			Image res = orig_img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

			BufferedImage resized_img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			resized_img.getGraphics().drawImage(res, 0, 0 , null);

			label = new JLabel(new ImageIcon(resized_img));
		} else {
			label = new JLabel(new ImageIcon(orig_img));
		}

		contentPane.add(label);
		pack();
		setVisible(true);

		label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Point p = e.getPoint();
				// Transform coordinates depending on the window's size
				int trans_x = (int)(p.getX() * (orig_w/(double)width));
				int trans_y = (int)(p.getY() * (orig_h/(double)height));

				Object obj = mxgc.getCellAt(trans_x, trans_y);
				System.out.println("x: " + p.getX() + "  y: " + p.getY());
				if (obj != null) {
					mxCell cell = (mxCell)obj;
					System.out.println(mxg.getLabel(cell));
				}
			}
		});
	}

}
