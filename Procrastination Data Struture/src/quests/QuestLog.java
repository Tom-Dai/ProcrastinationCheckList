package quests;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;


// QuestLog is a wrapper around an Arraylist of Quests
public class QuestLog implements Iterable<Quest>, Cloneable{
	
	private ArrayList<Quest> questLog;
	
	//this will read a file name and fill the contents into the quest log
	public QuestLog(String file){
		
		questLog= new ArrayList<Quest> ();
		try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
 
            while ((line = bufferedReader.readLine()) != null) {
                
            	Quest q = generateQuest(line);
            	
            	questLog.add(q);
                
            }
            bufferedReader.close();
            reader.close();
 
        } catch (IOException e) {
            return;
        }
	}
	//default contructor
	public QuestLog() {
		this("temp.txt");
	}
	
	/*This will save the current quest log into a file that can be read and loaded
	 * on program start-up
	 *  */
	public void saveQuests() {
		try {
			FileWriter writer = new FileWriter("temp.txt", false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            
            for(Quest q: questLog) {
            	StringBuffer string = new StringBuffer();
            	string.append(q.getType().toString()+'|'); //0
            	string.append(q.getTitle()+'|'); //1
            	string.append(q.getDescription()+'|' + q.getStatus()+ '|');//2 and 3
            	string.append(q.getDate().getYear() + '|');//4
            	string.append(q.getDate().getMonth() + '|');//5
            	string.append(q.getDate().getDay()+'|');//6
            	
            	if(q.getType()==QuestType.BINARY) {
            		string.append(q.getReward()+'\n');//7
            	} else if(q.getType()== QuestType.PROGRESSION) {
            		string.append(q.getReward()+'|'+ ((ProgressionQuest)q).getCount() //7 & 8
            				+'|'+ ((ProgressionQuest)q).getCondition()+'\n'); //9
            	} else if (q.getType() == QuestType.THRESHOLD) {
            		
            		String[] reward = ((ThresholdQuest)q).getThresholdRewards(); 
            		byte[] conditions = ((ThresholdQuest)q).getRewardConditions(); 
            		byte count = ((ThresholdQuest)q).getCount(); 
            		
            		string.append(reward.length + '|');//7;
            		for(String s: reward) {
            			string.append(s+'|');
            		}
            		for(byte b: conditions) {
            			string.append(b+'|');
            		}
            		string.append(count+'\n');//end
            	} else if (q.getType() == QuestType.COUNTER) {
            		string.append(q.getReward()+'|'+((CounterQuest)q).getCount()+'\n');
            	}
            	bufferedWriter.write(string.toString());
    		}
            
            bufferedWriter.close();
            writer.close();
		} catch (IOException e) {
			return;
		}
		
	}
	
	//adds a quest to the quest log
	public void addQuest(Quest quest) {
		questLog.add(quest);
	}
	
	//removes a quest from the questlog by title
	public void removeQuest(String title) {
		
	}
	// removes a quest from the questlog by reference
	public void removeQuest(Quest q) {
		questLog.remove(q);
	}
	
	//sorts the questLog by comparator
	public void sort(Comparator<Quest> c) {
		questLog.sort(c);
	}
	//removes all completed and failed quests from the questLog
	public void clearLog() {
		ArrayList<Quest> copy = new ArrayList<Quest>();
		
		for(Quest q: questLog) {
			if(q.getStatus()==QuestStatus.INCOMPLETE) copy.add(q);
		}
		
		questLog= copy;
	}
	//will write to an archive file of any finished quest, named by the date, then removed from list 
	public void archive() {
		
	}
	
	//The iterator does not contain a remove method
	public Iterator<Quest> iterator(){
		return new Iterator<Quest>() {
			int size = questLog.size(),curr=0;
			
			@Override
			public boolean hasNext() {
				return curr<size;
			}
			
			@Override
			public Quest next() {
				return questLog.get(curr);
			}
		};
	}
	/*
	 * The structure of the line should be this:
	 * Quest Type, Title, description, date
	 * 
	 * then depending on the type, there are some additional words to be added.
	 * 
	 * Binary: reward
	 * 
	 * Progression: current count and the condition and reward
	 * 
	 * Threshold: count, the array of conditions and array of rewards.
	 * 
	 * */
	public static Quest generateQuest(String s) {
		//using regex to split everything into an array
		String[] data = s.split("|");
		
		QuestType type= parseType(data[0]);
		String title = data[1], description= data[2];
		QuestStatus status= parseStatus(data[3]);
		Date date= new Date(Integer.parseInt(data[4]),Integer.parseInt(data[5]),Integer.parseInt(data[6]));
		Quest quest = null;
		if(type==QuestType.BINARY) {
			
			String reward = data[7];
			quest = new BinaryQuest(title, description, date, reward);
			quest.setStatus(status);
		} else if (type==QuestType.PROGRESSION) {
			
			String reward = data[7];
			quest = new ProgressionQuest(title,description,date,reward, (byte) Integer.parseInt(data[9]));
			( (ProgressionQuest)quest).setCount( (byte) Integer.parseInt(data[8]));
			quest.setStatus(status);
		} else if (type==QuestType.THRESHOLD) {
			
			int numElm = Integer.parseInt(data[7]);
			int start = 8 + numElm;
			byte[] conditions = new byte[numElm];
			String[] rewards = new String[numElm];
			
			for(int i=8, j=0; i< start; i++, j++) {
				rewards[j]= data[i];
			}
			for(int i = start, j=0;i < (start+numElm) ;i++, j++) {
				conditions[j] = (byte) Integer.parseInt(data[i]);
			}
			
			quest = new ThresholdQuest(title, description,date,rewards,conditions);
			
			( (ThresholdQuest)quest).setCount((byte)Integer.parseInt(data[data.length-1]));
			
			quest.setStatus(status);
		} else {
			
			quest = new CounterQuest(title,description,date, data[7]);
			((CounterQuest) quest).setCount((byte)Integer.parseInt(data[8]));
			
			quest.setStatus(status);
		}
		
		return quest;
		
	}
	
	public static QuestType parseType(String s) {
		if(s.equals("BINARY")) {
			return QuestType.BINARY;
		} else if(s.equals("PROGRESSION")) {
			return QuestType.PROGRESSION;
		} else if(s.equals("THRESHOLD")) {
			return QuestType.THRESHOLD;
		} else if(s.equals("COUNTER")) {
			return QuestType.COUNTER;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public static QuestStatus parseStatus(String s) {
		if(s.equals("INCOMPLETE")) {
			return QuestStatus.INCOMPLETE;
		} else if(s.equals("COMPLETED")) {
			return QuestStatus.COMPLETED;
		} else if(s.equals("FAILED")) {
			return QuestStatus.FAILED;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
}
