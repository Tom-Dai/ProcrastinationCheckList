import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements ActionListener {
	
	private JFrame frame;
	private JButton button;
	private JLabel begin;
	private JPanel panel;
	
	public GUI() {
		
		frame = new JFrame();
		
		button = new JButton("Start");
		button.addActionListener(this);
		
		begin = new JLabel("Click start to begin your adventure!");
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0, 1));
		panel.add(begin);
		panel.add(button);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setSize(2000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Procrastination Checklist");
		frame.pack();
		frame.setVisible(true);
		
	}

	public static void main(String[] args) {
		new GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		button.setVisible(false);
		begin.setVisible(false);
	}

}
