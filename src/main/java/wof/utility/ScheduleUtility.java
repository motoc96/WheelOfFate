package wof.utility;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import wof.model.DateAndShift;
import wof.model.Engineer;
import wof.model.Schedule;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ScheduleUtility {
    private static List<Engineer> engineerPool;
    private static int noOfEngToGenerate;
    private static List<DateAndShift> dateAndShiftPool;


    private int noOfEngineers = 10;
    private int noOfShifts = 2;
    private int noOfDays = 10;

    public ScheduleUtility() {
        super();
        engineerPool = new ArrayList<Engineer>(noOfEngineers);
        dateAndShiftPool = new ArrayList<DateAndShift>();
    }

    public List<Engineer> getEngineersPool() {
        noOfEngToGenerate = noOfEngineers - engineerPool.size();
        if (noOfEngToGenerate != 0) {
            List<Engineer> autoEngineerPool = autoGenerateEngineers(noOfEngToGenerate);
            engineerPool.addAll(autoEngineerPool);
        }
        return engineerPool;
    }

    private List<Engineer> autoGenerateEngineers(int noOfEngToGenerate) {
        List<Engineer> autoEngineerPool = new ArrayList<Engineer>();
        for (int i = 1; i <= noOfEngToGenerate; i++) {
            String randomName = RandomStringUtils.randomNumeric(1, 10).toUpperCase();
            autoEngineerPool.add(new Engineer(String.valueOf(i), randomName, 0));
        }
        Collections.shuffle(autoEngineerPool);
        System.out.println(autoEngineerPool);
        return autoEngineerPool;
    }

    public List<DateAndShift> getDateAndShiftPool() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        date = calendar.getTime();
        for (int i = 0; i < noOfDays; ) {
            if (calendar.get(Calendar.DAY_OF_WEEK) > 2 && i == 0) {
                calendar.add(Calendar.DAY_OF_MONTH, 9 - calendar.get(Calendar.DAY_OF_WEEK));
            } else if (calendar.get(Calendar.DAY_OF_WEEK) < 7 && calendar.get(Calendar.DAY_OF_WEEK) > 1) {
                date = calendar.getTime();
                for (int shift = 1; shift <= noOfShifts; shift++) {
                    dateAndShiftPool.add(new DateAndShift(date, shift));
                }
                i++;
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            } else {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
        return dateAndShiftPool;
    }

    public boolean checkEligibility(Engineer engineer, DateAndShift dateAndShift) {
        Boolean response = false;

        Calendar calendar = Calendar.getInstance();
        Date currentDate = dateAndShift.getDate();
        calendar.setTime(currentDate);
        int count = noOfShifts;
        ArrayList<Engineer> tempEngineerList = new ArrayList<Engineer>();
        while (count != 1) {
            for (DateAndShift tempDAS : dateAndShiftPool) {
                Calendar tempCalendar = Calendar.getInstance();
                tempCalendar.setTime(tempDAS.getDate());
                if (tempDAS.getEngineer() == engineer && tempCalendar.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) {
                    tempEngineerList.add(tempDAS.getEngineer());
                }
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            count--;
        }
        if (!tempEngineerList.contains(engineer))
            response = true;
        return response;
    }

    public void shuffle() {
        engineerPool.clear();
        getEngineersPool();
        dateAndShiftPool.clear();
        getDateAndShiftPool();
    }

    public String getDateAndShift(DateAndShift dateAndShift) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateAndShift.getDate());
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        dateFormat.setTimeZone(calendar.getTimeZone());
        String response = dateFormat.format(calendar.getTime() + "Shift: " + dateAndShift.getShift());
        return response;
    }

    public void getEngineers() {
        List<DateAndShift> dateAndShiftList = getDateAndShiftPool();
        List<Engineer> engineerList = getEngineersPool();
        for(Engineer engineer : engineerList){
            for(DateAndShift dateAndShift : dateAndShiftList){
                if(checkEligibility(engineer,dateAndShift) && engineer.getNoOfDaysWorked() <2 && !dateAndShift.isOccupied()) {
                    if(dateAndShift.getShift()==1 && !engineer.isShiftOne() && engineer.getNoOfDaysWorked() ==0){
                        dateAndShift.setEngineer(engineer);
                        engineer.setShiftOne(true);
                        dateAndShift.setOccupied(true);
                        engineer.addOneDaysWorked();
                    } else if(dateAndShift.getShift()==2 && !engineer.isShiftTwo() && engineer.getNoOfDaysWorked()==1){
                        dateAndShift.setEngineer(engineer);
                        engineer.setShiftTwo(true);
                        dateAndShift.setOccupied(true);
                        engineer.addOneDaysWorked();
                    }
                }
            }
        }
    }
}
