package wikipedia;


import wikipedia.presentation.*;
import java.awt.EventQueue;
//import wikipedia.presentation.*;

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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inici frame = new inici();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
