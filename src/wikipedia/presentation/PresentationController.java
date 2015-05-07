package wikipedia.presentation;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;

import wikipedia.domain.*;

/**
 * The Controller of Presentation Package
 * @author G13.2
 */
public class PresentationController
{
	/**
	 * The Controller of Domain
	 */
	public static DomainController dc;

	/**
	 * Create a PresentacionController
	 */
	public PresentationController() {
		dc = new DomainController();
	}

	/**
	 * Execute the Presentation Controller
	 */
	public void run() {
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
	
	public static void readWiki(ArrayList<String> a){
		dc.readWPformat(a);
	}
	
	public static void loadWiki(File f){
		dc.loadWP(f);
	}
}
