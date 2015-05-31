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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;

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
	private JButton btnBorrar;
	private JButton btnMod;
	private JButton btnCom;
	private JButton btnAddLink;
	private JButton btnDelLink;
	private GraphPanel gp;

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
			panel.setBounds(0,20,900,600);
			contentPane.add(panel);

			OGraph g = pc.getGraph();
			gp = new GraphPanel(g, 900, 600, cc);
			
			/**
			 * Al clickar sobre un item:
			 */
			gp.addOnItemClickListener(new GraphPanelOnItemClickListener() {
				public void onItemClick(String item) {
					if (item != lastItem && ((cc && item.contains("Node")) || !cc)) {
						setBounds((width/2)-450,(height/2)-300,1200,600);
						txtpn.setText(p.getProperty(pc.getLanguage()+"graph1") + " " + item +
							p.getProperty(pc.getLanguage()+"graph2"));
						node = item;
						lastItem = item;
						panel_2.setVisible(false);				// panel del recuadro
						buttonDo.setVisible(false);				// boton continuar
						textField.setVisible(false);			// recuadro de texto
						if (cc) {
							btnBorrar.setVisible(false);		// BORRAR
							btnMod.setVisible(false);			// MOD ELEMENT
							btnCom.setVisible(true);			// MOD COMMUNITY
							btnAddLink.setVisible(false);		// ADD LINK
							btnDelLink.setVisible(false);		// DEL LINK
						}
						else {
							btnBorrar.setVisible(true);
							btnCom.setVisible(false);
							if (item.contains("Node")) {
								btnAddLink.setVisible(true);
								btnDelLink.setVisible(true);
								btnMod.setVisible(true);
							}
							else {
								btnAddLink.setVisible(false);
								btnDelLink.setVisible(false);
								btnMod.setVisible(false);
							}
						}
					} else {
						setBounds((width/2)-450,(height/2)-300,900,600);
						panel_2.setVisible(false);
						buttonDo.setVisible(false);
						btnBorrar.setVisible(false);
						textField.setVisible(false);
						btnBorrar.setVisible(false);
						btnMod.setVisible(false);
						btnCom.setVisible(false);
						btnAddLink.setVisible(false);
						btnDelLink.setVisible(false);
						lastItem = null;
					}
				}
			});
			gp.setBackground(new Color(255, 255,255));
			panel.add(gp);

			/**
			 * Panel principal (grafo)
			 */
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
			btnBorrar = new JButton(p.getProperty(pc.getLanguage()+"deletegraph"));
			btnBorrar.setToolTipText(p.getProperty(pc.getLanguage()+"deletegraph_tool"));
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
			btnBorrar.setBounds(26, 276, 117, 25);
			panel_1.add(btnBorrar);
			btnBorrar.setVisible(false);

			/**
			 * Panel para el recuadro
			 */
			panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setBounds(57, 390, 129, 48);
			panel_2.setVisible(false);
			panel_1.add(panel_2);
			panel_2.setLayout(null);

			/**
			 * Recuadro para escribir
			 */
			textField = new JTextField();
			textField.setBounds(6, 16, 117, 25);
			panel_2.add(textField);
			textField.setColumns(10);

			buttonDo = new JButton("➤");
			buttonDo.setToolTipText(p.getProperty(pc.getLanguage()+"execute_tool"));
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
						else if (option == 3){
							pc.modCommunity(node_aux,textField.getText());
						}
						gp.refresh();
						setBounds((width/2)-450,(height/2)-300,900,600);
						panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
						panel_2.setVisible(false);
						buttonDo.setVisible(false);
						textField.setVisible(false);

						option = -1;
					}
					panel_2.setBorder(new TitledBorder(null, "", TitledBorder.CENTER, TitledBorder.TOP, null, null));
					textField.setText("");
					option = -1;
				}
			});
			buttonDo.setBounds(194, 402, 45, 23);
			panel_1.add(buttonDo);
			buttonDo.setVisible(false);

			/**
			 * Btn Modificar Element
			 */
			btnMod = new JButton(p.getProperty(pc.getLanguage()+"modifyelement"));
			btnMod.setToolTipText(p.getProperty(pc.getLanguage()+"modifyelement_tool"));
			btnMod.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					option = 0;
					textField.setVisible(true);
					buttonDo.setVisible(true);
					panel_2.setVisible(true);
					panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), p.getProperty(pc.getLanguage()+"modifyelement_2"), TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
				}
			});
			btnMod.setBounds(173, 276, 117, 25);
			panel_1.add(btnMod);
			btnMod.setVisible(false);

			/**
			 * Btn Modificar Comunitat
			 */
			btnCom = new JButton(p.getProperty(pc.getLanguage()+"modifycc"));
			btnCom.setToolTipText(p.getProperty(pc.getLanguage()+"modifycc_tool"));
			btnCom.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					option = 3;
					textField.setVisible(true);
					buttonDo.setVisible(true);
					panel_2.setVisible(true);
					panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), p.getProperty(pc.getLanguage()+"modifycc_2"), TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
				}
			});
			btnCom.setBounds(26, 276, 117, 25);
			panel_1.add(btnCom);

			/**
			 * Add Link
			 */
			btnAddLink = new JButton(p.getProperty(pc.getLanguage()+"addlink"));
			btnAddLink.setToolTipText(p.getProperty(pc.getLanguage()+"addlink_tool"));
			btnAddLink.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					option = 1;
					textField.setVisible(true);
					buttonDo.setVisible(true);
					panel_2.setVisible(true);
					panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), p.getProperty(pc.getLanguage()+"addlink_2"), TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
				}
			});
			btnAddLink.setBounds(26, 333, 117, 25);
			panel_1.add(btnAddLink);
			btnAddLink.setVisible(false);

			/**
			 * Del Link
			 */
			btnDelLink = new JButton(p.getProperty(pc.getLanguage()+"dellink"));
			btnDelLink.setToolTipText(p.getProperty(pc.getLanguage()+"dellink_tool"));
			btnDelLink.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					option = 2;
					textField.setVisible(true);
					buttonDo.setVisible(true);
					panel_2.setVisible(true);
					panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), p.getProperty(pc.getLanguage()+"dellink_2"), TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
				}
			});
			btnDelLink.setBounds(173, 333, 117, 25);
			panel_1.add(btnDelLink);
			btnCom.setVisible(false);

			/**
			* Menu File
			*/
			JMenuBar MenuFile = new JMenuBar();
			MenuFile.setBounds(0, 0, width, 21);
			contentPane.add(MenuFile);

			JMenu mnNewMenu_1 = new JMenu(p.getProperty(pc.getLanguage()+"file"));
			mnNewMenu_1.setHorizontalAlignment(SwingConstants.CENTER);
			MenuFile.add(mnNewMenu_1);

			JMenuItem menuItemExportGraph = new JMenuItem(p.getProperty(pc.getLanguage()+"export_graph"));
			mnNewMenu_1.add(menuItemExportGraph);

			menuItemExportGraph.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					wikipedia.utils.Utils.saveImage(gp.getImage(),
						p.getProperty(pc.getLanguage()+"save_image_dialog_title"));
				}
			});

			JMenuItem mntmNewMenuItem = new JMenuItem(p.getProperty(pc.getLanguage()+"exit"));
			mnNewMenu_1.add(mntmNewMenuItem);

			mntmNewMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});

			setVisible(true);

		}
		catch (Exception e) {
			System.out.println("ERROR");
		}
	}
}
