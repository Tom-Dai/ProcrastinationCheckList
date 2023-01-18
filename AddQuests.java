import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AddQuests implements ActionListener {

	private static JButton button;
	private static JLabel success;
	private static JLabel begin;
	
	private static JLabel QuestNameLabel;
	private static JLabel DescriptionLabel;
	private static JLabel DeadlineLabel;
	private static JLabel ClassLabel;
	private static JLabel TypeLabel;
	private static JLabel PointsLabel;
	
	private static JTextField QuestNameText;
	private static JTextField DescriptionText;
	private static JTextField DeadlineText;
	private static JTextField ClassText;
	private static JTextField TypeText;
	private static JTextField PointsText;
	
	public static void main(String[] args) {

		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		frame.setSize(350,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(panel);
		
		panel.setLayout(null);
		
		QuestNameLabel = new JLabel("Quest Name");
		QuestNameLabel.setBounds(10, 20, 80, 25);
		panel.add(QuestNameLabel);
		
		QuestNameText = new JTextField(20);
		QuestNameText.setBounds(100, 20, 165, 25);
		panel.add(QuestNameText);
		
		DescriptionLabel = new JLabel("Description");
		DescriptionLabel.setBounds(10, 50, 80, 25);
		panel.add(DescriptionLabel);
		
		DescriptionText = new JTextField(20);
		DescriptionText.setBounds(100, 50, 165, 25);
		panel.add(DescriptionText);
		
		DeadlineLabel = new JLabel("Deadline");
		DeadlineLabel.setBounds(10, 80, 80, 25);
		panel.add(DeadlineLabel);
		
		DeadlineText = new JTextField(20);
		DeadlineText.setBounds(100, 80, 165, 25);
		panel.add(DeadlineText);
		
		ClassLabel = new JLabel("Class");
		ClassLabel.setBounds(10, 110, 80, 25);
		panel.add(ClassLabel);
		
		ClassText = new JTextField(20);
		ClassText.setBounds(100, 110, 165, 25);
		panel.add(ClassText);
		
		TypeLabel = new JLabel("Type");
		TypeLabel.setBounds(10, 140, 80, 25);
		panel.add(TypeLabel);
		
		TypeText = new JTextField(20);
		TypeText.setBounds(100, 140, 165, 25);
		panel.add(TypeText);
		
		PointsLabel = new JLabel("Points");
		PointsLabel.setBounds(10, 170, 80, 25);
		panel.add(PointsLabel);
		
		PointsText = new JTextField(20);
		PointsText.setBounds(100, 170, 165, 25);
		panel.add(PointsText);
		
		button = new JButton("Add Quest");
		button.setBounds(10, 200, 150, 25);
		button.addActionListener(new AddQuests());
		panel.add(button);
		
		begin = new JLabel("Descriptions");
		
		success = new JLabel("");
		success.setBounds(10, 230, 300, 25);
		panel.add(success);
		
		frame.setVisible(true);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		button.setVisible(false);
		begin.setVisible(false);
		success.setText("Quest added successfully!");
	}
	/*CREATE LOGIN (if we want to add)
	 * 
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	
	 * @Override
	public void actionPerformed(ActionEvent e) {
		String user = userText.getText();
		String password = passwordText.getText();
		System.out.println(user + ", "+ password);
		
		if(user.equals("user") && password.equals("password")){
			success.setText("Quest added successfully!");
		}
	}
	 */

}
