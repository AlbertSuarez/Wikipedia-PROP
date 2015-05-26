package wikipedia.presentation;

import g13.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VistaGraph extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String node;
	
	/**
	 * Create the frame.
	 */
	public VistaGraph(final PresentationController pc, boolean cc) {
		super("Wikipedia");
		try {
			Properties p = new Properties();
			p.load(new FileInputStream("conf.ini"));
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
			final GraphPanel gp = new GraphPanel(g, 900, 600, cc);
			
			/**
			 * Button Delete
			 */
			JButton btnDelCategory = new JButton(p.getProperty(pc.getLanguage()+"deletegraph"));
			btnDelCategory.setToolTipText(p.getProperty(pc.getLanguage()+"deletegraph"));
			btnDelCategory.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (node.contains("Edge")) {
						
					}
					else {
						String node_aux = node.split(":")[1];
						if (pc.isCat(node_aux)) pc.delCat(node_aux);
						else pc.delPage(node_aux);
					}
					btnDelCategory.setVisible(false);
					gp.setSize(900, 600);
					gp.setSizeImage(900, 600);
				}
			});
			btnDelCategory.setBounds(750, 100, 117, 25);
			contentPane.add(btnDelCategory);
			btnDelCategory.setVisible(false);

			
			/**
			 * When click to node or edge
			 */
			gp.addOnItemClickListener(new GraphPanelOnItemClickListener() {
				public void onItemClick(String item) {
					System.out.println("Item click event: " + item);
					node = item;
					if (!cc) {
						gp.setSize(700, 600);
						gp.setSizeImage(700, 400);
						btnDelCategory.setVisible(true);
					}
				}
			});
			contentPane.add(gp);
			
			pack();
			setVisible(true);
		}
		catch (Exception e) {
			System.out.println("ERROR");
		}
	}

}
