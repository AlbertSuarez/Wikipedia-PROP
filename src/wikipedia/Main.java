package wikipedia;

import wikipedia.presentation.*;

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
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PresentationController pc = new PresentationController();
				pc.run();
			}
		});		
	}
}
