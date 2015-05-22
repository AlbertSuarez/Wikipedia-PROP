package wikipedia.presentation;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class VistaOptions extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private int option;
	//private boolean com = false;
	//private boolean graf = true;

	/**
	 * Create the frame.
	 */
	public VistaOptions(final PresentationController pc) {
		super("Wikipedia");
		try {
			Properties p = new Properties();
			p.load(new FileInputStream("conf.ini"));
			//com = (!pc.CCisEmpty() && pc.getGraph().getNodes().size() <= Integer.parseInt(p.getProperty("conf.maxcom")));
			//graf = (pc.getGraph().getNodes().size() <= Integer.parseInt(p.getProperty("conf.maxnodes")) && pc.getGraph().getNodes().size() > 0);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int)screenSize.getWidth();
			int height = (int)screenSize.getHeight();
			setBounds((width/2)-450,(height/2)-300,900,600);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(152, 251, 152));
			contentPane.setForeground(new Color(248, 248, 255));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
	
			/**
			 * Panel to put the outputs
			 */
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Wikipedia", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
			panel.setBounds(460, 189, 387, 361);
			contentPane.add(panel);
			panel.setLayout(null);
	
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(5, 17, 375, 339);
			panel.add(scrollPane);
	
			final JTextPane textPane = new JTextPane();
			textPane.setEditable(false);
			scrollPane.setViewportView(textPane);
			
			/**
			 * Button Change Graph
			 */
			JButton btnNewButton = new JButton(p.getProperty(pc.getLanguage()+"changegraph"));
			btnNewButton.setToolTipText(p.getProperty(pc.getLanguage()+"changegraph_tool"));
			btnNewButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					textPane.setText("");
					pc.cleanCC();
					pc.optionsToInici();
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnNewButton.setBounds(12, 39, 169, 31);
			contentPane.add(btnNewButton);
			
			/**
			 * Button Show Graph
			 */
			JButton btnShowGraph = new JButton(p.getProperty(pc.getLanguage()+"showgraph"));
			btnShowGraph.setToolTipText(p.getProperty(pc.getLanguage()+"showgraph_tool"));
			btnShowGraph.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					pc.optionsToGraph();
				}
			});
			btnShowGraph.setBounds(29, 483, 153, 31);
			contentPane.add(btnShowGraph);
			if (pc.getGraph().getNodes().size() > Integer.parseInt(p.getProperty("conf.maxnodes")) || pc.getGraph().getNodes().size() == 0) btnShowGraph.setVisible(false);
			
			/**
			 * Button Show CC
			 */
			JButton btnShowCc = new JButton(p.getProperty(pc.getLanguage()+"showcc"));
			btnShowCc.setToolTipText(p.getProperty(pc.getLanguage()+"showcc_tool"));
			btnShowCc.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (!pc.CCisEmpty()) pc.optionsToCC();
					else textPane.setText(p.getProperty(pc.getLanguage()+"ccempty"));
				}
			});
			btnShowCc.setBounds(238, 483, 153, 31);
			contentPane.add(btnShowCc);
			if (pc.CCisEmpty() || pc.sizeCC() > Integer.parseInt(p.getProperty("conf.maxcom"))) btnShowCc.setVisible(false);
	
			/**
			 * TextField to Add or Delete Category
			 */
			textField = new JTextField();
			textField.setBounds(29, 90, 104, 25);
			contentPane.add(textField);
			textField.setColumns(10);
	
			/**
			 * Button Add Category
			 */
			JButton btnAddCategory = new JButton(p.getProperty(pc.getLanguage()+"addcategory"));
			btnAddCategory.setToolTipText(p.getProperty(pc.getLanguage()+"addcategory_tool"));
			btnAddCategory.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(textField.getText() != ""){
						pc.addCat(textField.getText());
						textField.setText("");
						if (pc.getGraph().getNodes().size() == 1) {
							btnShowGraph.setVisible(true);
						}
						btnShowCc.setVisible(false);
					}
				}
			});
			btnAddCategory.setBounds(145, 90, 117, 25);
			contentPane.add(btnAddCategory);
	
			/**
			 * Button Delete Category
			 */
			JButton btnDelCategory = new JButton(p.getProperty(pc.getLanguage()+"delcategory"));
			btnDelCategory.setToolTipText(p.getProperty(pc.getLanguage()+"delcategory_tool"));
			btnDelCategory.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(textField.getText() != ""){
						pc.delCat(textField.getText());
						textField.setText("");
						if (pc.getGraph().getNodes().size() == 0) {
							btnShowGraph.setVisible(false);
						}
						btnShowCc.setVisible(false);
					}
				}
			});
			btnDelCategory.setBounds(274, 90, 117, 25);
			contentPane.add(btnDelCategory);
	
			/**
			 * Text Field to Add or Delete Page
			 */
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(29, 147, 104, 25);
			contentPane.add(textField_1);
	
			/**
			 * Button Add Page
			 */
			JButton btnAddPage = new JButton(p.getProperty(pc.getLanguage()+"addpage"));
			btnAddPage.setToolTipText(p.getProperty(pc.getLanguage()+"addpage_tool"));
			btnAddPage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(textField_1.getText() != ""){
						pc.addPage(textField_1.getText());
						textField_1.setText("");
						if (pc.getGraph().getNodes().size() == 1) {
							btnShowGraph.setVisible(true);
						}
						btnShowCc.setVisible(false);
					}
				}
			});
			btnAddPage.setBounds(145, 144, 117, 25);
			contentPane.add(btnAddPage);
	
			/**
			 * Button Delete Page
			 */
			JButton btnDelPage = new JButton(p.getProperty(pc.getLanguage()+"delpage"));
			btnDelPage.setToolTipText(p.getProperty(pc.getLanguage()+"delpage_tool"));
			btnDelPage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(textField_1.getText() != ""){
						pc.delPage(textField_1.getText());
						textField_1.setText("");
						if (pc.getGraph().getNodes().size() == 0) {
							btnShowGraph.setVisible(false);
						}
						btnShowCc.setVisible(false);
					}
				}
			});
			btnDelPage.setBounds(274, 144, 117, 25);
			contentPane.add(btnDelPage);
			
			/**
			 * Number of communities (NG)
			 */
			JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spinner.setBounds(847, 147, 40, 25);
			spinner.setToolTipText(p.getProperty(pc.getLanguage()+"spinner_tool"));
			contentPane.add(spinner);
			
			/**
			 * Box to select algorithm
			 */
			final JComboBox<String> comboBox = new JComboBox<String> ();
			comboBox.setModel(new DefaultComboBoxModel<String> (new String[] {"Newmann-Girvan", "Louvain", "Clique percolation"}));
			comboBox.setBounds(666, 147, 169, 24);
			comboBox.setToolTipText(p.getProperty(pc.getLanguage()+"boxalgorithm"));
			contentPane.add(comboBox);
			comboBox.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
					if(comboBox.getSelectedIndex() == 0)spinner.setVisible(true);
					else spinner.setVisible(false);
	            }
	        });
	
			/**
			 * Button Community Detection
			 */
			JButton btnCommunityDetection = new JButton(p.getProperty(pc.getLanguage()+"communitydetection"));
			btnCommunityDetection.setToolTipText(p.getProperty(pc.getLanguage()+"communitydetection_tool"));
			btnCommunityDetection.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					pc.cleanCC();
					textPane.setText(pc.communityDetection(comboBox.getSelectedIndex(),(Integer)spinner.getValue()));
					if (!pc.CCisEmpty() && pc.sizeCC() <= Integer.parseInt(p.getProperty("conf.maxcom"))) btnShowCc.setVisible(true);
					else btnShowCc.setVisible(false);
				}
			});
			btnCommunityDetection.setFont(new Font("Dialog", Font.BOLD, 12));
			btnCommunityDetection.setBounds(460, 147, 194, 25);
			contentPane.add(btnCommunityDetection);
	
			/**
			 * Button Print Graph
			 */
			JButton btnPrintGraph = new JButton(p.getProperty(pc.getLanguage()+"printgraph"));
			btnPrintGraph.setToolTipText(p.getProperty(pc.getLanguage()+"printgraph_tool"));
			btnPrintGraph.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					textPane.setText(pc.printGraph());
				}
			});
			btnPrintGraph.setBounds(460, 90, 194, 25);
			contentPane.add(btnPrintGraph);
	
			/**
			 * Button Save Graph
			 */
			JButton btnSaveGraph = new JButton(p.getProperty(pc.getLanguage()+"savegraph"));
			btnSaveGraph.setToolTipText(p.getProperty(pc.getLanguage()+"savegraph_tool"));
			btnSaveGraph.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					pc.saveGraph();
				}
			});
			btnSaveGraph.setBounds(666, 90, 169, 25);
			contentPane.add(btnSaveGraph);
			
			/**
			 * Two Text Fields to Add or Delete Links or Modify Element or Community
			 */
			final JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(23, 372, 129, 48);
			contentPane.add(panel_1);
			panel_1.setLayout(null);
			
			textField_2 = new JTextField();
			textField_2.setBounds(6, 16, 117, 25);
			panel_1.add(textField_2);
			textField_2.setColumns(10);
			
			final JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(null, "", TitledBorder.CENTER, TitledBorder.TOP, null, null));
			panel_2.setBounds(178, 372, 129, 48);
			contentPane.add(panel_2);
			panel_2.setLayout(null);
			
			textField_3 = new JTextField();
			textField_3.setBounds(6, 16, 117, 25);
			panel_2.add(textField_3);
			textField_3.setColumns(10);
			
			/**
			 * Button Add Link
			 */
			JButton btnAddLink = new JButton(p.getProperty(pc.getLanguage()+"addlink"));
			btnAddLink.setToolTipText(p.getProperty(pc.getLanguage()+"addlink_tool"));
			btnAddLink.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					textField_2.setText("");
					textField_3.setText("");
					panel_1.setBorder(new TitledBorder(null, p.getProperty(pc.getLanguage()+"addlink_1"), TitledBorder.CENTER, TitledBorder.TOP, null, null));
					panel_2.setBorder(new TitledBorder(null, p.getProperty(pc.getLanguage()+"addlink_2"), TitledBorder.CENTER, TitledBorder.TOP, null, null));
					option = 0;
				}
			});
			btnAddLink.setBounds(29, 269, 117, 25);
			contentPane.add(btnAddLink);
	
			/**
			 * Button Delete Link
			 */
			JButton btnDelLink = new JButton(p.getProperty(pc.getLanguage()+"dellink"));
			btnDelLink.setToolTipText(p.getProperty(pc.getLanguage()+"dellink_tool"));
			btnDelLink.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					textField_2.setText("");
					textField_3.setText("");
					panel_1.setBorder(new TitledBorder(null, p.getProperty(pc.getLanguage()+"dellink_1"), TitledBorder.CENTER, TitledBorder.TOP, null, null));
					panel_2.setBorder(new TitledBorder(null, p.getProperty(pc.getLanguage()+"dellink_2"), TitledBorder.CENTER, TitledBorder.TOP, null, null));
					option = 1;
				}
			});
			btnDelLink.setBounds(184, 269, 117, 25);
			contentPane.add(btnDelLink);
			
			/**
			 * Button Modify Element
			 */
			JButton btnModifyElement = new JButton(p.getProperty(pc.getLanguage()+"modifyelement"));
			btnModifyElement.setToolTipText(p.getProperty(pc.getLanguage()+"modifyelement_tool"));
			btnModifyElement.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textField_2.setText("");
					textField_3.setText("");
					panel_1.setBorder(new TitledBorder(null, p.getProperty(pc.getLanguage()+"modifyelement_1"), TitledBorder.CENTER, TitledBorder.TOP, null, null));
					panel_2.setBorder(new TitledBorder(null, p.getProperty(pc.getLanguage()+"modifyelement_2"), TitledBorder.CENTER, TitledBorder.TOP, null, null));
					option = 2;
				}
			});
			btnModifyElement.setBounds(29, 324, 117, 25);
			contentPane.add(btnModifyElement);
			
			/**
			 * Button Modify CC
			 */
			JButton btnModifyCc = new JButton(p.getProperty(pc.getLanguage()+"modifycc"));
			btnModifyCc.setToolTipText(p.getProperty(pc.getLanguage()+"modifycc_tool"));
			btnModifyCc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					textField_2.setText("");
					textField_3.setText("");
					panel_1.setBorder(new TitledBorder(null, p.getProperty(pc.getLanguage()+"modifycc_1"), TitledBorder.CENTER, TitledBorder.TOP, null, null));
					panel_2.setBorder(new TitledBorder(null, p.getProperty(pc.getLanguage()+"modifycc_2"), TitledBorder.CENTER, TitledBorder.TOP, null, null));
					option = 3;
				}
			});
			btnModifyCc.setBounds(184, 324, 117, 25);
			contentPane.add(btnModifyCc);
			
			/**
			 * Button Continue
			 */
			JButton button = new JButton("➤");
			button.setToolTipText(p.getProperty(pc.getLanguage()+"execute_tool"));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(option == 0)pc.addLink(textField_2.getText(),textField_3.getText());
					else if (option == 1)pc.delLink(textField_2.getText(),textField_3.getText());
					else if (option == 2)pc.modElement(textField_2.getText(),textField_3.getText());
					else if (option == 3 && !pc.CCisEmpty()){
						pc.modCommunity(textField_2.getText(),textField_3.getText());
						textPane.setText(pc.printCC());
					}
					panel_1.setBorder(new TitledBorder(null, "", TitledBorder.CENTER, TitledBorder.TOP, null, null));
					panel_2.setBorder(new TitledBorder(null, "", TitledBorder.CENTER, TitledBorder.TOP, null, null));
					textField_2.setText("");
					textField_3.setText("");
					option = -1;
				}
			});
			button.setBounds(342, 389, 49, 23);
			contentPane.add(button);
			
			/**
			 * Button Validate Golden
			 */
			JButton btnValidateGolden = new JButton(p.getProperty(pc.getLanguage()+"validategolden"));
			btnValidateGolden.setToolTipText(p.getProperty(pc.getLanguage()+"validategolden_tool"));
			btnValidateGolden.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					textPane.setText(p.getProperty(pc.getLanguage()+"validategolden_res") + pc.calculateGolden());
				}
			});
			btnValidateGolden.setBounds(577, 39, 153, 31);
			contentPane.add(btnValidateGolden);
			
			/**
			 * Menu Empty
			 */
			JMenuBar MenuEmpty = new JMenuBar();
			MenuEmpty.setBounds(150, 0, 770, 21);
			contentPane.add(MenuEmpty);
			
			JMenu mnNewMenu = new JMenu("");
			MenuEmpty.add(mnNewMenu);
			
			/**
			 * Menu Language
			 */
			JMenuBar MenuLanguage = new JMenuBar();
			MenuLanguage.setBounds(60, 0, 90, 21);
			contentPane.add(MenuLanguage);
			
			JMenu mnLanguage = new JMenu(p.getProperty(pc.getLanguage()+"language"));
			mnLanguage.setHorizontalAlignment(SwingConstants.CENTER);
			MenuLanguage.add(mnLanguage);
			
			JMenuItem mntmEnglish = new JMenuItem("English");
			mnLanguage.add(mntmEnglish);
			mntmEnglish.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (!pc.getLanguage().equals("english.")) {
		            	pc.setLanguage("english.");
		                pc.closeOptions();
		                pc.refreshOptions();
	                }
	            }
	        });
			
			JMenuItem mntmCastell = new JMenuItem("Español");
			mnLanguage.add(mntmCastell);
			mntmCastell.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (!pc.getLanguage().equals("spanish.")) {
		                pc.setLanguage("spanish.");
		                pc.closeOptions();
		                pc.refreshOptions();
	                }
	            }
	        });
			
			JMenuItem mntmCatala = new JMenuItem("Català");
			mnLanguage.add(mntmCatala);
			mntmCatala.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (!pc.getLanguage().equals("catalan.")) {
		                pc.setLanguage("catalan.");
		                pc.closeOptions();
		                pc.refreshOptions();
	                }
	            }
	        });
			
			/**
			 * Menu File
			 */
			JMenuBar MenuFile = new JMenuBar();
			MenuFile.setBounds(0, 0, 60, 21);
			contentPane.add(MenuFile);
			
			JMenu mnNewMenu_1 = new JMenu(p.getProperty(pc.getLanguage()+"file"));
			mnNewMenu_1.setHorizontalAlignment(SwingConstants.CENTER);
			MenuFile.add(mnNewMenu_1);
			
			JMenuItem mntmNewMenuItem = new JMenuItem(p.getProperty(pc.getLanguage()+"exit"));
			mnNewMenu_1.add(mntmNewMenuItem);
			
			mntmNewMenuItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                pc.closeOptions();
	            }
	        });
			
		}
		catch (Exception e) {
			System.out.println("ERROR");
		}
		
	}
}