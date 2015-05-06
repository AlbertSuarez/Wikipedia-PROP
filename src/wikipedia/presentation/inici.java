package wikipedia.presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.TextArea;
import java.awt.Label;
import java.awt.Panel;

import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import static wikipedia.utils.Utils.*;
import javax.swing.JMenuBar;
import java.awt.Scrollbar;
import javax.swing.JScrollPane;

public class inici extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public inici() {
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
		
		JButton btnContinue = new JButton("LOAD GRAPH");
		btnContinue.setBounds(251, 460, 131, 27);
		contentPane.add(btnContinue);
		btnContinue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				File f = Choose();
				if(f != null){
					// lectura de grafo y llamada a ventana de operaciones
				}
			}
		});
		btnContinue.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JTextPane txtpnWelcomeToThe = new JTextPane();
		txtpnWelcomeToThe.setEditable(false);
		txtpnWelcomeToThe.setFont(new Font("Droid Sans", Font.PLAIN, 43));
		txtpnWelcomeToThe.setBackground(new Color(255, 165, 0));
		txtpnWelcomeToThe.setText("Welcome to the wikipedia project");
		txtpnWelcomeToThe.setBounds(28, 42, 843, 113);
		contentPane.add(txtpnWelcomeToThe);
		
		JButton btnWriteGraph = new JButton("WRITE GRAPH");
		btnWriteGraph.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				new write().setVisible(true);
			}
		});
		btnWriteGraph.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnWriteGraph.setBounds(590, 436, 136, 27);
		contentPane.add(btnWriteGraph);
		
		JTextPane txtpnToStartDoing = new JTextPane();
		txtpnToStartDoing.setEditable(false);
		txtpnToStartDoing.setFont(new Font("Arial", Font.PLAIN, 16));
		txtpnToStartDoing.setBackground(new Color(255, 165, 0));
		txtpnToStartDoing.setText("To start doing things, please load a graph\r\n in wikipedia format or write it on screen");
		txtpnToStartDoing.setBounds(285, 282, 329, 47);
		contentPane.add(txtpnToStartDoing);
	}
}
