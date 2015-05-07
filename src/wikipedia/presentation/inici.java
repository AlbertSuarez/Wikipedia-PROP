package wikipedia.presentation;

import static wikipedia.utils.Utils.*;
import static wikipedia.presentation.PresentationController.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class inici extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	public inici() {
		super("Wikipedia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBounds(200,200,900,600);
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
		btnContinue.setBounds(142, 387, 216, 60);
		contentPane.add(btnContinue);
		btnContinue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				File f = Choose();
				if(f != null){
					loadWiki(f);
					setVisible(false); //you can't see me!
					dispose();
					new options().setVisible(true);
				}
			}
		});
		btnContinue.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		/**
		 * Button Write Graph
		 */
		JButton btnWriteGraph = new JButton("WRITE GRAPH");
		btnWriteGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnWriteGraph.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				new write().setVisible(true);
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
