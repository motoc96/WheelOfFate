package wof.model;

import java.util.Date;

public class Schedule {
    private Engineer engineer;
    private DateAndShift dateAndShift;

    public Schedule(Engineer engineer, DateAndShift dateAndShift) {
        super();
        this.engineer = engineer;
        this.dateAndShift = dateAndShift;
    }

    public Engineer getEngineer() { return engineer; }
    public void setEngineer(Engineer engineer) { this.engineer = engineer; }
    public DateAndShift getDateAndShift() { return dateAndShift; }
    public void setDateAndShift(DateAndShift dateAndShift) { this.dateAndShift = dateAndShift; }
}
