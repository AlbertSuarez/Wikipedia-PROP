package wikipedia.presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Introduce the Wikipedia's Graph", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel.setBounds(7, 83, 886, 482);
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
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.setFont(new Font("Droid Sans", Font.BOLD, 30));
		btnNewButton.setLocation(12, 12);
		btnNewButton.setSize(230, 59);
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
		JButton btnContinue = new JButton("CONTINUE");
		btnContinue.setFont(new Font("Droid Sans", Font.BOLD, 30));
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
		btnContinue.setBounds(658, 12, 230, 59);
		contentPane.add(btnContinue);
		
		
	}
}
