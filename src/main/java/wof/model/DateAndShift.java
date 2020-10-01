package wof.model;

import java.util.Date;
import java.util.Optional;

public class DateAndShift {
    private Date date;
    private int shift;
    private boolean isOccupied=false;
    private Engineer engineer;
    public DateAndShift(Date date, int shift){
        super();
        this.date=date;
        this.shift=shift;
    }

    public boolean isOccupied() { return isOccupied; }
    public void setOccupied(boolean occupied) { isOccupied = occupied; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public int getShift() { return shift; }
    public void setShift(int shift) { this.shift = shift; }
    public Engineer getEngineer() { return engineer; }
    public void setEngineer(Engineer engineer) { this.engineer = engineer; }
}
