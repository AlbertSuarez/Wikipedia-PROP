package wikipedia.presentation;

import g13.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class VistaGraph extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String node;
	private String lastItem;
	private JTextPane txtpn;
	private JTextField textField;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton buttonDo;
	private int option = -1;

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
			final GraphPanel gp = new GraphPanel(g, 900, 600, cc);
			gp.addOnItemClickListener(new GraphPanelOnItemClickListener() {
				public void onItemClick(String item) {
					if (item != lastItem) {
						setBounds((width/2)-450,(height/2)-300,1200,600);
						txtpn.setText(p.getProperty(pc.getLanguage()+"graph1") + " " + item +
							p.getProperty(pc.getLanguage()+"graph2"));
						node = item;
						lastItem = item;
						panel_2.setVisible(true);
						buttonDo.setVisible(true);
					} else {
						setBounds((width/2)-450,(height/2)-300,900,600);
						panel_2.setVisible(false);
						buttonDo.setVisible(false);
						lastItem = null;
					}
				}
			});
			gp.setBackground(new Color(255, 255,255));
			panel.add(gp);

			panel_1 = new JPanel();
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

					String node_aux = node.split(":")[1];

					if (node.contains("Node")) {
						if (pc.isCat(node_aux)) pc.delCat(node_aux);
						else pc.delPage(node_aux);
					}
					else {
						String[] split = node_aux.split(", ");

						String a = split[0].substring(1);
						String b = split[1];

						pc.delLink(a, b);
					}
					gp.refresh();
					setBounds((width/2)-450,(height/2)-300,900,600);
				}
			});
			btnBorrar.setBounds(26, 276, 100, 23);
			panel_1.add(btnBorrar);
			if (cc) btnBorrar.setVisible(false);

			/*
			 * panel  i boton continuar
			 */

			panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setBounds(57, 390, 98, 43);
			panel_2.setVisible(false);
			panel_1.add(panel_2);
			panel_2.setLayout(null);

			textField = new JTextField();
			textField.setBounds(6, 16, 86, 20);
			panel_2.add(textField);
			textField.setColumns(10);

			buttonDo = new JButton("➤");
			buttonDo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((!textField.getText().isEmpty() && !textField.getText().contains(" "))) {
						String node_aux = node.split(":")[1];
						if(option == 0){
							pc.modElement(node_aux,textField.getText());
						}
						else if (option == 1){
							pc.addLink(node_aux,textField.getText());
						}
						else if (option == 2){
							pc.delLink(node_aux,textField.getText());
						}
						gp.refresh();
						setBounds((width/2)-450,(height/2)-300,900,600);
						panel_2.setVisible(false);
						buttonDo.setVisible(false);
						option = -1;
					}
					panel_2.setBorder(new TitledBorder(null, "", TitledBorder.CENTER, TitledBorder.TOP, null, null));
					textField.setText("");
					option = -1;
				}
			});
			buttonDo.setBounds(194, 402, 45, 23);
			buttonDo.setVisible(false);
			panel_1.add(buttonDo);

			/**
			 * Btn Modificar Element
			 */
			JButton btnMod = new JButton(p.getProperty(pc.getLanguage()+"modgraph"));
			btnMod.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					option = 0;
					buttonDo.setVisible(true);
					panel_2.setVisible(true);
				}
			});
			btnMod.setBounds(173, 276, 100, 23);
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

			/*
			 * afegeix un link csupc amb el node indicat
			 */
			JButton btnAddLink = new JButton("add link");
			btnAddLink.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					option = 1;
					buttonDo.setVisible(true);
					panel_2.setVisible(true);
				}
			});
			btnAddLink.setBounds(26, 333, 100, 23);
			panel_1.add(btnAddLink);

			JButton btnDelLink = new JButton("del link");
			btnDelLink.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					option = 2;
					buttonDo.setVisible(true);
					panel_2.setVisible(true);
				}
			});
			btnDelLink.setBounds(173, 333, 100, 23);
			panel_1.add(btnDelLink);

			if (!cc) btnCom.setVisible(false);

			setVisible(true);

		}
		catch (Exception e) {
			System.out.println("ERROR");
		}
	}
}
