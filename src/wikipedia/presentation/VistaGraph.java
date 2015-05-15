package wikipedia.presentation;

import g13.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.view.mxGraph;

public class VistaGraph extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public VistaGraph(PresentationController pc) {
		super("Wikipedia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		setBounds((width/2)-450,(height/2)-300,900,600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		OGraph g = pc.getGraph();
		final mxGraph mxg = g.toMxGraph();
		final mxGraphComponent mxgc = new mxGraphComponent(mxg);

		BufferedImage image = mxCellRenderer.createBufferedImage(mxg, null, 1, Color.WHITE, true, null);

		JLabel label = new JLabel(new ImageIcon(image));
		contentPane.add(label);
		pack();
		setVisible(true);

		label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Point p = e.getPoint();
				Object obj = mxgc.getCellAt((int)p.getX(), (int)p.getY());
				System.out.println("x: " + p.getX() + "  y: " + p.getY());
				if (obj != null) {
					mxCell cell = (mxCell)obj;
					System.out.println(mxg.getLabel(cell));
				}
			}
		});
	}

}
