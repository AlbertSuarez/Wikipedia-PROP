package wikipedia;

import g13.*;
import wikipedia.domain.*;
import wikipedia.presentation.*;
import wikipedia.persistence.*;

import java.io.File;
import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.mxgraph.view.mxGraph;
import com.mxgraph.util.mxCellRenderer;

import javax.swing.*;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.model.mxCell;

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

		OGraph g = GraphIO.readGraphWPformat();
		mxGraph mxg = g.toMxGraph();
		mxGraphComponent mxgc = new mxGraphComponent(mxg);

		BufferedImage image = mxCellRenderer.createBufferedImage(mxg, null, 1, Color.WHITE, true, null);

		JFrame frame = new JFrame("Graph image");
		JLabel label = new JLabel(new ImageIcon(image));
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

		/*try {
			ImageIO.write(image, "PNG", new File("graph.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Done!");
		}*/

		//PresentationController pc = new PresentationController();
		//pc.run();
	}
}
