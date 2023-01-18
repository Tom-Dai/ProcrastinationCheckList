import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class GUI {
	
	private static JFrame frame;
	private static JButton startButton;
	private static JLabel beginLabel;
	private static JPanel homePanel;
	
	private static JButton questButton;
	private static JLabel questLabel;
	private static JPanel questPanel;
	private static JList quests;
	private static DefaultListModel listModel;
	
	public static void main(String[] args) {
		
		/* Set up of buttonListeners */
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				
				if (o == startButton) {
					homePanel.setVisible(false);
					frame.add(questPanel, BorderLayout.CENTER);
				} else if (o == questButton) {
					addQuest();
					frame.add(questPanel, BorderLayout.CENTER);
				}
			}
			
		};
		
		
		
		/* GUI for home page */	
		beginLabel = new JLabel("Click start to begin your adventure!");
		
		startButton = new JButton("Start");
		startButton.addActionListener(buttonListener);	
		
		homePanel = new JPanel();
		homePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		homePanel.setLayout(new GridLayout(0, 1));
		homePanel.add(beginLabel);
		homePanel.add(startButton);
		
		/* GUI for quest page */
		questLabel = new JLabel("My Quests");
		
		questButton = new JButton("Add New Quest");
		questButton.addActionListener(buttonListener);
		
		listModel = new DefaultListModel();
		listModel.addElement("item 1");
		quests = new JList(listModel);
		
		questPanel = new JPanel();
		questPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		questPanel.setLayout(new GridLayout(0,1));
		questPanel.add(questLabel);
		questPanel.add(quests);
		questPanel.add(questButton);
		
		/* GUI for add quest page */
		/* Add GUI code for the pop up for adding details of the quest */
		/* should be new frame */
			
		frame = new JFrame();
		
		frame.add(homePanel, BorderLayout.CENTER);
		frame.setSize(4000, 3000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Procrastination Checklist");
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void addQuest() {
		listModel.addElement("new item"); // connect to the quest implementation code
	}
	
	public static void completedQuest() {
		
	}

}
