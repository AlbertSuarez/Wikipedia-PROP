package wikipedia.presentation;

import g13.*;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VistaGraph extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public VistaGraph(final PresentationController pc) {
		super("Wikipedia");
		setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int width = (int)screenSize.getWidth();
		final int height = (int)screenSize.getHeight();
		setBounds((width/2)-450,(height/2)-300,900,600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		OGraph g = pc.getGraph();
		GraphPanel gp = new GraphPanel(g, 900, 600);
		gp.addOnItemClickListener(new GraphPanelOnItemClickListener() {
			public void onItemClick(String item) {
				System.out.println("Item click event: " + item);
			}
		});

		contentPane.add(gp);
		pack();
		setVisible(true);
	}

}
