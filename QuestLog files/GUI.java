import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.Date;

public class GUI {

  private static JFrame frame;
  private static JButton startButton;
  private static JLabel beginLabel;
  private static JPanel homePanel;

  private static JButton questButton;
  private static JButton changeStatusButton;
  private static JLabel questLabel;
  private static JLabel totalPointsLabel;
  private static int totalPoints = 0;
  private static JPanel questPanel;
  private static JList quests;
  private static DefaultListModel listModel;

  /* Add Quest Page Components */
  private static JButton addQuestButton;
  private static JLabel success;
  private static JLabel begin;

  private static JLabel QuestNameLabel;
  private static JLabel DescriptionLabel;
  private static JLabel DeadlineLabel;
  // private static JLabel TypeLabel;
  private static JLabel PointsLabel;

  private static JTextField QuestNameText;
  private static JTextField DescriptionText;
  private static JTextField DeadlineText;
  // private static JTextField TypeText;
  private static JTextField PointsText;

  private static JFrame statusFrame;
  private static JPanel statusPanel;
  private static JButton completeButton;
  private static JButton incompleteButton;

  private static JButton saveButton;
  
  public static QuestLog quest;

  public static int holder;

  //runs the GUI on the Event dispatching thread
  public static void main(String[] args) {
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                new GUI();
            }
        });
  }
  
  public GUI() {

    /* Set up of buttonListeners */
    quest = new QuestLog();
    
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
        } else if (o == changeStatusButton) {
          completedQuest();
          frame.add(questPanel, BorderLayout.CENTER);
        } else if (o == saveButton){
          quest.saveQuests();
        }
      }

    };
    // holds our quests

    /* GUI for home page */
    beginLabel = new JLabel("Click start to begin your adventure!");

    startButton = new JButton("Start");
    startButton.addActionListener(buttonListener);

    homePanel = new JPanel();
    homePanel.setBorder(BorderFactory.createEmptyBorder(100, 225, 100, 225));
    homePanel.setLayout(new GridLayout(0, 1));
    homePanel.add(beginLabel);
    homePanel.add(startButton);

    /* GUI for quest page */
    questLabel = new JLabel("My Quests");

    completeButton = new JButton("Complete Quest");
    completeButton.setBounds(10, 150, 150, 25);
    completeButton.addActionListener(buttonListener);
    
    questButton = new JButton("Add New Quest");
    changeStatusButton = new JButton("Change Status of Selected Quest");
    questButton.setBounds(10, 200, 150, 25);
    questButton.addActionListener(buttonListener);

    //i dunno if this is the correct bound
    saveButton = new JButton("Save Current Quests");
    saveButton.setBounds(10 + 75, 200, 150, 25);
    saveButton.addActionListener(buttonListener);

    

    listModel = new DefaultListModel<String>();
    quests = new JList(listModel);
    //loads our saved quests into the list
    for(Quest q: quest){
      q.updateStatus();
      listModel.addElement(q.toString());
      if(q.isCompleted()) totalPoints+= Integer.parseInt(q.getReward());
    }

    totalPointsLabel = new JLabel("Total Points: " + totalPoints);
    
    questPanel = new JPanel();
    questPanel.setLayout(new GridLayout(0, 1));
    questPanel.add(questLabel);
    questPanel.add(quests);
    questPanel.add(questButton);
    questPanel.add(saveButton);
    questPanel.add(totalPointsLabel);
    
    //questPanel.add(saveButton);
    frame = new JFrame();

    frame.add(homePanel, BorderLayout.CENTER);
    frame.setSize(700, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Procrastination Checklist");
    frame.pack();
    frame.setVisible(true);

    //need to add a button that saves the current questlog into a file.

    completeButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        completeQuest();
      }
    });
  }

  public static void completeQuest() {
    totalPointsLabel.setText("Total Points: " + holder);
  }

  public static void addQuest() {

    JPanel addQuestPanel = new JPanel();
    JFrame addQuestFrame = new JFrame();

    ActionListener addButtonListener = new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent ae) {
        Object o = ae.getSource();

        if (o == addQuestButton) {
          //scans our textfields and creates a new quest
          try{
            String title = QuestNameText.getText();
            String description = DescriptionText.getText();
            String reward = PointsText.getText();
            //checks to see if it can be parsed into an integer
            Integer.parseInt(reward);
            Date d = new Date(Date.parse(DeadlineText.getText()));
            if(d.before(new Date())) throw new IllegalArgumentException();
            BinaryQuest q = new BinaryQuest(title, description, d, reward);
            quest.addQuest(q);

            questPanel.add(completeButton);
            holder = Integer.parseInt(reward);

            listModel.addElement(q.toString());

            addQuestButton.setVisible(false);
            begin.setVisible(false);
            success.setText("Quest added successfully!");
            addQuestFrame.dispose();
            
          } catch (Exception e) {
            success.setText("Quest addition error!");
          }
          
        }
      }

    };
    // shows the list of quests
    
    addQuestFrame.setSize(500, 300);
    addQuestPanel.setLayout((LayoutManager) new BoxLayout(addQuestPanel, BoxLayout.Y_AXIS));
    addQuestFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    addQuestFrame.setVisible(true);
    addQuestFrame.add(addQuestPanel);

    QuestNameLabel = new JLabel("Quest Name");
    QuestNameLabel.setBounds(10, 50, 80, 25);
    addQuestPanel.add(QuestNameLabel);

    QuestNameText = new JTextField(20);
    QuestNameText.setBounds(100, 50, 165, 25);
    addQuestPanel.add(QuestNameText);

    DescriptionLabel = new JLabel("Description");
    DescriptionLabel.setBounds(10, 50, 80, 25);
    addQuestPanel.add(DescriptionLabel);

    DescriptionText = new JTextField(20);
    DescriptionText.setBounds(100, 50, 165, 25);
    addQuestPanel.add(DescriptionText);

    DeadlineLabel = new JLabel("Deadline (MM/DD/YYYY");
    DeadlineLabel.setBounds(10, 50, 80, 25);
    addQuestPanel.add(DeadlineLabel);

    DeadlineText = new JTextField(20);
    DeadlineText.setBounds(100, 50, 165, 25);
    addQuestPanel.add(DeadlineText);

    /*
     * TypeLabel = new JLabel("Type");
     * TypeLabel.setBounds(10, 140, 80, 25);
     * addQuestPanel.add(TypeLabel);
     * 
     * TypeText = new JTextField(20);
     * TypeText.setBounds(100, 140, 165, 25);
     * addQuestPanel.add(TypeText);
     */
    PointsLabel = new JLabel("Points for Completion (Must be a number)");
    PointsLabel.setBounds(10, 50, 80, 25);
    addQuestPanel.add(PointsLabel);

    PointsText = new JTextField(20);
    PointsText.setBounds(100, 50, 165, 25);
    addQuestPanel.add(PointsText);

    /*
     * yearsLabel = new JLabel("Reward for Completion");
     * yearsLabel.setBounds(10, 170, 80, 25);
     * addQuestPanel.add(PointsLabel);
     * 
     * yearsText = new JTextField(20);
     * yearsText.setBounds(100, 170, 165, 25);
     * addQuestPanel.add(PointsText);
     */

    addQuestButton = new JButton("Add Quest");
    addQuestButton.setBounds(150, 200, 150, 25);
    addQuestButton.addActionListener(addButtonListener);
    addQuestPanel.add(addQuestButton);

    begin = new JLabel("Descriptions");

    success = new JLabel("");
    success.setBounds(150, 230, 300, 25);
    addQuestPanel.add(success);

    addQuestFrame.setVisible(true);

    /*
     * String user = userText.getText();
     * String password = passwordText.getText();
     * System.out.println(user + ", "+ password);
     * 
     * if(user.equals("user") && password.equals("password")){
     * success.setText("Quest added successfully!");
     * }
     */
    
    
  }

  public static void completedQuest() {

      int index = quests.getSelectedIndex();
      String s = (String) quests.getSelectedValue();
    
      statusPanel = new JPanel();
      statusFrame = new JFrame();

      completeButton = new JButton();
      incompleteButton = new JButton();
    
      ActionListener statusListener = new ActionListener() {
        
        @Override public void actionPerformed(ActionEvent e) {
          Object o = e.getSource();

          if (o == completeButton) {
            Quest q = quest.getQuest(index);
            q.tickUp((byte) 0);
            totalPoints+=Integer.parseInt(q.getReward());
            listModel.set(index,q.toString());
          } else if (o == incompleteButton) {
            statusFrame.dispose();
          }
        }
      };
      
      
      statusFrame.setSize(500, 300);
      statusFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      statusFrame.setVisible(true);
      statusFrame.add(statusPanel);
 
      
      statusPanel.add(completeButton);
      statusPanel.add(incompleteButton);
      completeButton.addActionListener(statusListener);
      incompleteButton.addActionListener(statusListener);
    
  }
}
