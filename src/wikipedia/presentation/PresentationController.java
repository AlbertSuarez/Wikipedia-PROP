package wikipedia.presentation;

import java.awt.EventQueue;
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
	public DomainController dc;

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
}
