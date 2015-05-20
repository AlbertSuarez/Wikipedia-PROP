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

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class VistaOptions extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private int option;
	private boolean com = false;

	/**
	 * Create the frame.
	 */
	public VistaOptions(final PresentationController pc) {
		super("Wikipedia");
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

		JButton btnNewButton = new JButton("Change Graph");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pc.optionsToInici();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(12, 12, 169, 31);
		contentPane.add(btnNewButton);

		textField = new JTextField();
		textField.setBounds(29, 90, 104, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnAddCategory = new JButton("Add Category");
		btnAddCategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(textField.getText() != ""){
					pc.addCat(textField.getText());
					textField.setText("");
				}
			}
		});
		btnAddCategory.setBounds(145, 90, 117, 25);
		contentPane.add(btnAddCategory);

		JButton btnDelCategory = new JButton("Del category");
		btnDelCategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(textField.getText() != ""){
					pc.delCat(textField.getText());
					textField.setText("");
				}
			}
		});
		btnDelCategory.setBounds(274, 90, 117, 25);
		contentPane.add(btnDelCategory);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(29, 147, 104, 25);
		contentPane.add(textField_1);

		JButton btnAddPage = new JButton("Add Page");
		btnAddPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(textField_1.getText() != ""){
					pc.addPage(textField_1.getText());
					textField_1.setText("");
				}
			}
		});
		btnAddPage.setBounds(145, 144, 117, 25);
		contentPane.add(btnAddPage);

		JButton btnDelPage = new JButton("Del Page");
		btnDelPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(textField_1.getText() != ""){
					pc.delPage(textField_1.getText());
					textField_1.setText("");
				}
			}
		});
		btnDelPage.setBounds(274, 144, 117, 25);
		contentPane.add(btnDelPage);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Wikipedia", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel.setBounds(492, 189, 385, 361);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 17, 375, 339);
		panel.add(scrollPane);

		final JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);

		final JComboBox<String> comboBox = new JComboBox<String> ();
		comboBox.setModel(new DefaultComboBoxModel<String> (new String[] {"Newmann-Girvan", "Louvain", "Clique percolation"}));
		comboBox.setBounds(703, 144, 169, 24);
		contentPane.add(comboBox);

		JButton btnCommunityDetection = new JButton("Community Detection");
		btnCommunityDetection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textPane.setText(pc.communityDetection(comboBox.getSelectedIndex()));
				com = true;
			}
		});
		btnCommunityDetection.setFont(new Font("Dialog", Font.BOLD, 12));
		btnCommunityDetection.setBounds(497, 144, 194, 25);
		contentPane.add(btnCommunityDetection);

		JButton btnPrintGraph = new JButton("Print Graph");
		btnPrintGraph.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textPane.setText(pc.printGraph());
			}
		});
		btnPrintGraph.setBounds(497, 90, 194, 25);
		contentPane.add(btnPrintGraph);

		JButton btnSaveGraph = new JButton("Save Graph");
		btnSaveGraph.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pc.saveGraph();
			}
		});
		btnSaveGraph.setBounds(703, 90, 169, 25);
		contentPane.add(btnSaveGraph);

		JButton btnShowGraph = new JButton("Show Graph");
		btnShowGraph.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pc.optionsToGraph();
			}
		});
		btnShowGraph.setBounds(29, 483, 153, 31);
		contentPane.add(btnShowGraph);
		
		JButton btnShowCc = new JButton("Show CC");
		btnShowCc.setBounds(238, 483, 153, 31);
		contentPane.add(btnShowCc);
		
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
		
		JButton btnAddLink = new JButton("Add Link");
		btnAddLink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_2.setText("");
				textField_3.setText("");
				panel_1.setBorder(new TitledBorder(null, "father's name", TitledBorder.CENTER, TitledBorder.TOP, null, null));
				panel_2.setBorder(new TitledBorder(null, "son's name", TitledBorder.CENTER, TitledBorder.TOP, null, null));
				option = 0;
			}
		});
		btnAddLink.setBounds(29, 269, 117, 25);
		contentPane.add(btnAddLink);

		JButton btnDelLink = new JButton("Del Link");
		btnDelLink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_2.setText("");
				textField_3.setText("");
				panel_1.setBorder(new TitledBorder(null, "father's name", TitledBorder.CENTER, TitledBorder.TOP, null, null));
				panel_2.setBorder(new TitledBorder(null, "son's name", TitledBorder.CENTER, TitledBorder.TOP, null, null));
				option = 1;
			}
		});
		btnDelLink.setBounds(184, 269, 117, 25);
		contentPane.add(btnDelLink);
		
		
		JButton btnModifyElement = new JButton("modify Element");
		btnModifyElement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText("");
				textField_3.setText("");
				panel_1.setBorder(new TitledBorder(null, "old name", TitledBorder.CENTER, TitledBorder.TOP, null, null));
				panel_2.setBorder(new TitledBorder(null, "new name", TitledBorder.CENTER, TitledBorder.TOP, null, null));
				option = 2;
			}
		});
		btnModifyElement.setBounds(29, 324, 117, 25);
		contentPane.add(btnModifyElement);
		
		JButton btnModifyCc = new JButton("Modify CC");
		btnModifyCc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_2.setText("");
				textField_3.setText("");
				panel_1.setBorder(new TitledBorder(null, "node", TitledBorder.CENTER, TitledBorder.TOP, null, null));
				panel_2.setBorder(new TitledBorder(null, "new community", TitledBorder.CENTER, TitledBorder.TOP, null, null));
				option = 3;
			}
		});
		btnModifyCc.setBounds(184, 324, 117, 25);
		contentPane.add(btnModifyCc);
		
		JButton button = new JButton("-->");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(option == 0)pc.addLink(textField_2.getText(),textField_3.getText());
				else if (option == 1)pc.delLink(textField_2.getText(),textField_3.getText());
				else if (option == 2)pc.modElement(textField_2.getText(),textField_3.getText());
				else if (option == 3 && com){
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
		
		JButton btnValidateGolden = new JButton("Validate Golden");
		btnValidateGolden.setBounds(622, 39, 153, 31);
		contentPane.add(btnValidateGolden);

	}
}
