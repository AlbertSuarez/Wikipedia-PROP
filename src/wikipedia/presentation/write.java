package wikipedia.presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class write extends JFrame {

	private JPanel contentPane;
	private JTextField txtIntroduceTheWikipedias;


	/**
	 * Create the frame.
	 */
	public write() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 49, 392, 212);
		contentPane.add(scrollPane);
		
		final JTextArea txtrFafas = new JTextArea();
		scrollPane.setViewportView(txtrFafas);
		txtrFafas.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 12));
		txtrFafas.setDropMode(DropMode.INSERT);
		
		
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.setLocation(12, 12);
		btnNewButton.setSize(70, 25);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false); //you can't see me!
				dispose();
				new inici().setVisible(true);
				
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnContinue = new JButton("CONTINUE");
		btnContinue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String wr = txtrFafas.toString();
				// en wr tienes el texto escrito en el panel
				
			}
		});
		btnContinue.setBounds(330, 12, 104, 25);
		contentPane.add(btnContinue);
		
		txtIntroduceTheWikipedias = new JTextField();
		txtIntroduceTheWikipedias.setBackground(new Color(50, 205, 50));
		txtIntroduceTheWikipedias.setFont(new Font("Liberation Mono", Font.BOLD, 11));
		txtIntroduceTheWikipedias.setHorizontalAlignment(SwingConstants.CENTER);
		txtIntroduceTheWikipedias.setText("Introduce the Wikipedia's Graph");
		txtIntroduceTheWikipedias.setBounds(94, 15, 224, 19);
		contentPane.add(txtIntroduceTheWikipedias);
		txtIntroduceTheWikipedias.setColumns(10);
	}
}
