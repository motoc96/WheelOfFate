package wof.model;

public class Engineer {
    private String id;
    private String name;
    private int noOfDaysWorked;
    private boolean shiftOne =false;
    private boolean shiftTwo =false;

    public Engineer(String id, String name, int noOfDaysWorked) {
        super();
        this.id = id;
        this.name = name;
        this.noOfDaysWorked = noOfDaysWorked;
    }

    public boolean isShiftOne() {
        return shiftOne;
    }
    public void setShiftOne(boolean shiftOne) {
        this.shiftOne = shiftOne;
    }
    public boolean isShiftTwo() {
        return shiftTwo;
    }
    public void setShiftTwo(boolean shiftTwo) {
        this.shiftTwo = shiftTwo;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getNoOfDaysWorked() { return noOfDaysWorked; }
    public void addOneDaysWorked() { this.noOfDaysWorked = noOfDaysWorked+1; }
}
