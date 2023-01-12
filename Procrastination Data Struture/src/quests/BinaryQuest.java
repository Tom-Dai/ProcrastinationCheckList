package quests;

import java.util.Date;


public class BinaryQuest extends Quest{

	private String reward;
	
	public BinaryQuest(String title, String description,  Date date, String reward) {
		super(title, description, QuestType.BINARY, date);
		this.reward=reward;
	}
	
	@Override
	public String getReward() {
		return reward;
	}
	
	//This will only need to be called once
	@Override
	public void tickUp(byte n) {
		if(super.getStatus()!= QuestStatus.FAILED) {
			super.setStatus(QuestStatus.COMPLETED);
		}
	}
	
}
