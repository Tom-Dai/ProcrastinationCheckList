package quests;

import java.util.Date;

public class ProgressionQuest extends Quest {
	
	private String reward;
	byte condition, currentCount;
	
	public ProgressionQuest(String title, String description,  Date date, String reward, byte condition) {
		super(title, description, QuestType.PROGRESSION, date);
		this.reward=reward;
		currentCount=0;
		this.condition= condition;
	}
	
	@Override
	public String getReward() {
		return reward;
	}
	
	//This will not count beyond its condition
	@Override
	public void tickUp(byte n) {
		//shortcircuit
		if(currentCount==condition) return;
		
		if(super.getStatus()!= QuestStatus.FAILED) {
			currentCount+=n;
			if(currentCount>condition) currentCount=condition;
			if(super.getStatus()==QuestStatus.INCOMPLETE && condition==currentCount) {
				super.setStatus(QuestStatus.COMPLETED);
			}
		}
	}
	public byte getCondition() {
		return condition;
	}
	
	public byte getCount() {
		return this.currentCount;
	}
	
	public void setCount(byte count) {
		this.currentCount=count;
	}
}
