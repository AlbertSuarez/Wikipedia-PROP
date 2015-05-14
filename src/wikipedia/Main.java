package wikipedia;

import g13.*;
import wikipedia.domain.*;
import wikipedia.presentation.*;
import wikipedia.persistence.*;
import static wikipedia.utils.Utils.*;
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

		/*OGraph g = GraphIO.loadWP(Choose());
		final mxGraph mxg = g.toMxGraph();
		final mxGraphComponent mxgc = new mxGraphComponent(mxg);

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

		try {
			ImageIO.write(image, "PNG", new File("graph.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Done!");
		}*/
		
		//PresentationController pc = new PresentationController();
		//pc.run();
		

		DomainController dc = new DomainController();
		dc.loadWP(Choose());
		
		print("Clique Percolation Maxim Algorithm");
		print("");
		dc.runCPMaxim();
		dc.printCC();
		print("");
		
		print("4-Clique Percolation Algorithm");
		print("");
		dc.runCPFour();
		dc.printCC();
		print("");
		
		print("Louvain Algorithm");
		print("");
		dc.runLouvain();
		dc.printCC();
		print("");
		
		print("NewmanGirvan Algorithm");
		print("");
		dc.runNG(15);
		dc.printCC();
		print("");
		
		
		
	}
}
