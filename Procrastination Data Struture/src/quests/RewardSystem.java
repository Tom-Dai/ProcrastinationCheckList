import java.util.*

public class RewardSystem {

    private int points;
    private String rewardCategory;
    private boolean check;

    public RewardSystem(int points, String rewardCategory) {
        this.points = points;
        this.rewardCategory = rewardCategory
    }

    //Default Constructor
    public RewardSystem() {
        this(200, "Intermediate")
    }

    public boolean setCategory(String rewardCategory) {
        if(rewardCategory == null || rewardCategory.length() < 1) {
            return false;
        } else if(rewardCategory.toLowerCase().equals("novice") || rewardCategory.toLowerCase().equals("intermediate") || rewardCategory.toLowerCase().equals("advanced")) {
            if(rewardCategory.toLowerCase().equals("novice")) {
                this.points = 100;
            } else if(rewardCategory.toLowerCase().equals("intermediate")) {
                this.points = 200;
            } else if(rewardCategory.toLowerCase().equals("advanced")) {
                this.points = 300;
            }
            this.check = true;
            this.rewardCategory = new String(rewardCategory);
            return true;
        }
    }

    public String getCategory() {
        return this.rewardCategory;
    }

    //Must have called setCategory first before doing getPoints
    public int getPoints() {
        if(check) {
            return this.points;
        } else {
            throw new IllegalArgumentException("Must call method setCategory() before callign getPoints()");
        }
    }

}