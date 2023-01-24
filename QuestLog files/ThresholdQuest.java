

import java.util.Date;

public class ThresholdQuest extends Quest {
	private String[] reward;
	byte[] conditions;
	byte count;
	
	public ThresholdQuest(String title, String description,  Date date, String[] reward, byte[] condition) {
		super(title, description, QuestType.PROGRESSION, date);
		this.reward=reward;
	}
	
	@Override
	public String getReward() {
		StringBuffer string = new StringBuffer();
		for(int i=0; i< reward.length;i++) {
			string.append(conditions[i]+": "+reward[i]+'\n');
		}
		string.deleteCharAt(string.length()-1);
		return string.toString();
	}
	
	@Override
	public void tickUp(byte n) {
		if(super.getStatus()!= QuestStatus.FAILED) {
			count+=n;
			if(super.getStatus()==QuestStatus.INCOMPLETE && count>=conditions[0]) {
				super.setStatus(QuestStatus.COMPLETED);
			}
		}
	}
	
	public byte[] getRewardConditions() {
		return conditions.clone();
	}
	
	public byte getCount() {
		return this.count;
	}
	
	public String [] getThresholdRewards() {
		return reward.clone();
	}
	
	public void setCount(byte count) {
		this.count=count;
	}
}
