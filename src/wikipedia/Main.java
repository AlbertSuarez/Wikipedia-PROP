package wikipedia;

import wikipedia.presentation.*;
import wikipedia.persistence.*;
import g13.*;
import java.util.*;
import java.io.File;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.view.mxGraph;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxUtils;

/**
 * Main Class
 * @author G13.2
 */
public class Main
{
	/**
	* The main method
	* @param args Arguments of main
	*/
	public static void main(String[] args) {

		OGraph g = GraphIO.loadWP(new File("golden/ENTRADAcodigo.txt"));
		mxGraph mxg = g.toMxGraph();

		final mxGraphComponent mxgc = new mxGraphComponent(mxg);
		mxGraphModel mxgm = (mxGraphModel)mxgc.getGraph().getModel();
		Collection<Object> cells = mxgm.getCells().values();
		mxUtils.setCellStyles(mxgc.getGraph().getModel(),
			cells.toArray(), mxConstants.STYLE_ENDARROW, mxConstants.NONE);



		BufferedImage image = mxCellRenderer.createBufferedImage(mxg, null, 1, Color.WHITE, true, null);


		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(0.7, 0.7);
		AffineTransformOp scaleOp =
		   new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		after = scaleOp.filter(image, after);

		JFrame frame = new JFrame("Graph image");
		JLabel label = new JLabel(new ImageIcon(after));
		frame.getContentPane().add(label);
		frame.pack();
		frame.setVisible(true);


		label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Point p = e.getPoint();
				Object obj = mxgc.getCellAt((int)p.getX(), (int)p.getY());
				System.out.println("x: " + p.getX() + "  y: " + p.getY());
				if (obj != null) {
					mxCell cell = (mxCell)obj;
					System.out.println(mxg.getLabel(cell));
				}
			}
		});


		/*javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PresentationController pc = new PresentationController();
				pc.run();
			}
		});*/
	}
}
