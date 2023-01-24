

import java.util.Date;

public class CounterQuest extends Quest {
	private String reward;
	byte count;
	
	public CounterQuest(String title, String description,  Date date, String reward) {
		super(title, description, QuestType.PROGRESSION, date);
		this.reward=reward;
		count=0;
	}
	
	@Override
	public String getReward() {
		return reward;
	}
	
	@Override
	public void tickUp(byte n) {
		if(super.getStatus()==QuestStatus.INCOMPLETE) count+=n;
	}
	//Counter Quests will always be completed
	@Override 
	public void updateStatus() {
		if(super.getDate().before(new Date())) {
			super.setStatus(QuestStatus.COMPLETED);
		}
	}
	public byte getCount() {
		return this.count;
	}
	
	public void setCount(byte b) {
		count=b;
	}
}
