package wikipedia;

import g13.*;
import wikipedia.domain.*;
import wikipedia.presentation.*;
import wikipedia.persistence.*;

import java.io.File;
import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.mxgraph.view.mxGraph;
import com.mxgraph.util.mxCellRenderer;

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
	public static void main(String[] args){

		/*OGraph g = GraphIO.readGraphWPformat();
		mxGraph mxg = g.toMxGraph();

		BufferedImage image = mxCellRenderer.createBufferedImage(mxg, null, 1, Color.WHITE, true, null);

		try {
			ImageIO.write(image, "PNG", new File("graph.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Done!");
		}*/

		PresentationController pc = new PresentationController();
		pc.run();
	}
}
