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

public class VistaOptions extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public VistaOptions(PresentationController pc) {
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
				}
			}
		});
		btnDelPage.setBounds(274, 144, 117, 25);
		contentPane.add(btnDelPage);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Newmann-Girvan", "Louvain", "Clique percolation"}));
		comboBox.setBounds(703, 144, 169, 24);
		contentPane.add(comboBox);
		
		JButton btnCommunityDetection = new JButton("Community Detection");
		btnCommunityDetection.setFont(new Font("Dialog", Font.BOLD, 12));
		btnCommunityDetection.setBounds(497, 144, 194, 25);
		contentPane.add(btnCommunityDetection);
		
		JButton btnPrintGraph = new JButton("Print Graph");
		btnPrintGraph.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pc.printGraph();
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Wikipedia", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel.setBounds(492, 189, 385, 361);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 17, 375, 339);
		panel.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
		
	}
}
