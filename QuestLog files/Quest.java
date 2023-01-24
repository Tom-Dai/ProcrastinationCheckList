

import java.util.Date;

public abstract class Quest implements Comparable<Quest> {
	
	private QuestType type;
	private QuestStatus status;
	private String title;
	private String description;
	private Date date;
	
	public Quest(String title, String description, QuestType type, Date date) {
		this.type=type;
		this.title=title;
		this.description=description;
		this.date=date;
		status= QuestStatus.INCOMPLETE;
	}
	/*This will increment the count of what is needed of the checklist */
	public abstract void tickUp(byte n);
	
	/*Returns the string of what the desired reward is*/
	public abstract String getReward();
	
	/*returns the quest type */
	public QuestType getType() {
		return type;
	}
	/*Returns the quest status */
	public QuestStatus getStatus() {
		return status;
	}
	/*Can set the status of the quest. */
	public void setStatus(QuestStatus status) {
		this.status=status;
	}
	//returns the title of the quest
	public String getTitle() {
		return title;
	}
	// returns the quest description
	public String getDescription() {
		return description;
	}
	
	//NOTE: the date return is mutable and can be modified
	public Date getDate() {
		return date;
	}
	//Will change the quest status if the deadline has passed
	public void updateStatus() {
		if(status==QuestStatus.INCOMPLETE) {
			if(date.before(new Date())) {
				status= QuestStatus.FAILED;
			}
		}
	}
	//returns a boolean to see if the quest status is completed
	public boolean isCompleted() {
		return status==QuestStatus.COMPLETED;
	}
	// The natural hierarchy: Incomplete, complete, failed.
	@Override
	public int compareTo(Quest other) {
		byte first=0, last=0;
		
		switch(status) {
			case INCOMPLETE:
				first = 1;
				break;
			case COMPLETED:
				first = 2;
				break;
			case FAILED:
				first = 3;
				break;
		}
		
		switch(other.status) {
		case INCOMPLETE:
			last = 1;
			break;
		case COMPLETED:
			last = 2;
			break;
		case FAILED:
			last = 3;
			break;
		}
		
		return first - last;
	}
	//The string representation of a Quest
	@Override
	public String toString() {
		StringBuffer string = new StringBuffer();
		string.append("Title: " + title + " | ");
		string.append("Description: " + description + " | ");
		string.append("Reward: " + getReward() + " | ");
		string.append("Date: " + date.toString() + " | ");
		string.append("Status: " + status.toString());
		return string.toString();
	}
	
	
}
