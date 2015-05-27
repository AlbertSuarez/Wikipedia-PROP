package wikipedia.presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.util.*;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

public class VistaWrite extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public VistaWrite(final PresentationController pc) {
		super("Wikipedia");
		try {
			Properties p = new Properties();
			p.load(new FileInputStream("conf.ini"));
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
			 * Panel of Text Area
			 */
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), p.getProperty(pc.getLanguage()+"panel_graph"), TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
			panel.setBounds(7, 100, 886, 469);
			contentPane.add(panel);
			panel.setLayout(null);
			
			/**
			 * Scroll of Text Area
			 */
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(5, 17, 876, 460);
			panel.add(scrollPane);
			
			/**
			 * Text Area
			 */
			final JTextArea txtrFafas = new JTextArea();
			scrollPane.setViewportView(txtrFafas);
			txtrFafas.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 18));
			txtrFafas.setDropMode(DropMode.INSERT);
			
			/**
			 * Button Back
			 */
			JButton btnNewButton = new JButton(p.getProperty(pc.getLanguage()+"back"));
			btnNewButton.setFont(new Font("Droid Sans", Font.BOLD, 30));
			btnNewButton.setLocation(12, 29);
			btnNewButton.setSize(230, 59);
			btnNewButton.setToolTipText(p.getProperty(pc.getLanguage()+"back_tool"));
			btnNewButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					pc.writeToInici();				
				}
			});
			contentPane.add(btnNewButton);
			
			/**
			 * Button Continue
			 */
			JButton btnContinue = new JButton(p.getProperty(pc.getLanguage()+"continue"));
			btnContinue.setFont(new Font("Droid Sans", Font.BOLD, 30));
			btnContinue.setToolTipText(p.getProperty(pc.getLanguage()+"continue_tool"));
			btnContinue.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String wr = txtrFafas.getText();
					if(wr != ""){
						pc.readWiki(new ArrayList<String>(Arrays.asList(wr.split("\n"))));
						pc.writeToOptions();
					}
				}
			});
			btnContinue.setBounds(658, 29, 230, 59);
			contentPane.add(btnContinue);
			
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
		                pc.closeWrite();
		                pc.refreshWrite();
	                }
	            }
	        });
			
			JMenuItem mntmCastell = new JMenuItem("Español");
			mnLanguage.add(mntmCastell);
			mntmCastell.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (!pc.getLanguage().equals("spanish.")) {
		                pc.setLanguage("spanish.");
		                pc.closeWrite();
		                pc.refreshWrite();
	                }
	            }
	        });
			
			JMenuItem mntmCatala = new JMenuItem("Català");
			mnLanguage.add(mntmCatala);
			mntmCatala.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (!pc.getLanguage().equals("catalan.")) {
		                pc.setLanguage("catalan.");
		                pc.closeWrite();
		                pc.refreshWrite();
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
	                pc.closeWrite();
	            }
	        });
		}
		catch (Exception e) {
			System.out.println("ERROR");
		}
	}
}
