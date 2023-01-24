

import java.util.Comparator;

public class QuestComparators {

	//Also seen in the compareTo method of the abstract Quest Class
	public static Comparator<Quest> defaultComparator(){
		
		return (a,b)->{
			byte first=0, last=0;
			
			switch(a.getStatus()) {
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
			
			switch(b.getStatus()) {
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
		};
	}
	//Sorts Alphabetically by the title 
	public static Comparator<Quest> alphabeticalComparator(){
		
		return (a,b)->{
			return a.getTitle().compareTo(b.getTitle());
		};
		
	}
	//Sorts Chronologically by the date
	public static Comparator<Quest> chronologicalComparator(){
		
		return (a,b)->{
			return a.getDate().compareTo(b.getDate());
		};
	}
}
