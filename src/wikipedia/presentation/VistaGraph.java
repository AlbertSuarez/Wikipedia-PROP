package wikipedia.presentation;

import g13.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class VistaGraph extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String node;
	private JTextPane txtpn;
	
	/**
	 * Create the frame.
	 */
	public VistaGraph(final PresentationController pc, boolean cc) {
		super("Wikipedia");
		try {
			final Properties p = new Properties();
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
			contentPane.setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBounds(0,0,900,600);
			contentPane.add(panel);

			OGraph g = pc.getGraph();
			GraphPanel gp = new GraphPanel(g, 900, 600, cc);
			gp.addOnItemClickListener(new GraphPanelOnItemClickListener() {
				public void onItemClick(String item) {
					System.out.println("Item click event: " + item);
					setBounds((width/2)-450,(height/2)-300,1200,600);
					txtpn.setText(p.getProperty(pc.getLanguage()+"graph1") + " " + item +
						p.getProperty(pc.getLanguage()+"graph2"));
					node = item;
				}
			});
			gp.setBackground(new Color(255, 255,255));
			panel.add(gp);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(900, 0, 300, 600);
			contentPane.add(panel_1);
			panel_1.setBackground(new Color(255, 255,255));
			panel_1.setLayout(null);
			
			txtpn = new JTextPane();
			txtpn.setText("");
			txtpn.setEditable(false);
			txtpn.setBounds(10, 134, 280, 76);
			panel_1.add(txtpn);
			
			/**
			 * Btn Borrar
			 */
			JButton btnBorrar = new JButton(p.getProperty(pc.getLanguage()+"deletegraph"));
			btnBorrar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (node.contains("Node")) {
						String node_aux = node.split(":")[1];
						if (pc.isCat(node_aux)) pc.delCat(node_aux);
						else pc.delPage(node_aux);
					}
					else {
						
					}
					setBounds((width/2)-450,(height/2)-300,900,600);
				}
			});
			btnBorrar.setBounds(26, 276, 89, 23);
			panel_1.add(btnBorrar);
			if (cc) btnBorrar.setVisible(false);
			
			/**
			 * Btn Modificar Element
			 */
			JButton btnMod = new JButton(p.getProperty(pc.getLanguage()+"modgraph"));
			btnMod.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (node.contains("Node")) {
						String node_aux = node.split(":")[1];
						pc.modElement(node_aux,"NOU_NOM");
					}
					setBounds((width/2)-450,(height/2)-300,900,600);
				}
			});
			btnMod.setBounds(173, 276, 89, 23);
			panel_1.add(btnMod);
			if (cc) btnMod.setVisible(false);
			
			/**
			 * Btn Modificar Comunitat
			 */
			JButton btnCom = new JButton(p.getProperty(pc.getLanguage()+"comgraph"));
			btnCom.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (node.contains("Node")) {
						String node_aux = node.split(":")[1];
						pc.modCommunity(node_aux,"1");		// Hi ha un 1 com comunitat exemple
					}
					setBounds((width/2)-450,(height/2)-300,900,600);
				}
			});
			btnCom.setBounds(26, 276, 89, 23);
			panel_1.add(btnCom);
			if (!cc) btnCom.setVisible(false);
			
			setVisible(true);

			/*
			OGraph g = pc.getGraph();
			final GraphPanel gp = new GraphPanel(g, 900, 600, cc);
			
			/**
			 * Button Delete
			 */
			/*JButton btnDelCategory = new JButton(p.getProperty(pc.getLanguage()+"deletegraph"));
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
			/*gp.addOnItemClickListener(new GraphPanelOnItemClickListener() {
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
			setVisible(true);*/
		}
		catch (Exception e) {
			System.out.println("ERROR");
		}
	}

}
