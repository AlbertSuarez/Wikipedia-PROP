package wikipedia.presentation;

import static wikipedia.utils.Utils.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaInici extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	public VistaInici(PresentationController pc) {
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
		
		/**
		 * Subtitle
		 */
		JTextPane txtpnToStartDoing = new JTextPane();
		txtpnToStartDoing.setEditable(false);
		txtpnToStartDoing.setFont(new Font("Arial", Font.PLAIN, 32));
		txtpnToStartDoing.setBackground(new Color(255, 165, 0));
		txtpnToStartDoing.setText("To start doing things, please load a graph\r\n in wikipedia format or write it on screen");
		txtpnToStartDoing.setBounds(142, 192, 583, 82);
		contentPane.add(txtpnToStartDoing);
		
		/**
		 * Button Load Graph
		 */
		JButton btnContinue = new JButton("LOAD GRAPH");
		btnContinue.setToolTipText("This option displays a window"
                + " to select a file containing a graph in WP format");
		btnContinue.setBounds(142, 387, 216, 60);
		contentPane.add(btnContinue);
		btnContinue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				File f = Choose();
				if(f != null){
					pc.loadWiki(f);
					pc.iniciToOptions();
				}
			}
		});
		btnContinue.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		/**
		 * Button Write Graph
		 */
		JButton btnWriteGraph = new JButton("WRITE GRAPH");
		btnWriteGraph.setToolTipText("This option allows handwrite a graph in WP format");
		btnWriteGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnWriteGraph.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pc.iniciToWrite();
			}
		});
		btnWriteGraph.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnWriteGraph.setBounds(509, 387, 216, 60);
		contentPane.add(btnWriteGraph);
		
		/**
		 * Title
		 */
		JTextPane txtpnWelcomeToThe = new JTextPane();
		txtpnWelcomeToThe.setEditable(false);
		txtpnWelcomeToThe.setFont(new Font("Dialog", Font.PLAIN, 56));
		txtpnWelcomeToThe.setBackground(new Color(255, 165, 0));
		txtpnWelcomeToThe.setText("Welcome to the wikipedia project");
		txtpnWelcomeToThe.setBounds(22, 116, 830, 78);
		contentPane.add(txtpnWelcomeToThe);
	}
}