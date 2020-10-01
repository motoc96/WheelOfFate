package wof.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wof.model.DateAndShift;
import wof.model.Engineer;
import wof.utility.ScheduleUtility;

import java.util.LinkedHashMap;
import java.util.Map;


@Component
public class ScheduleServiceImpl implements ScheduleService{


    private int noOfShifts =2;

    private int shiftsToComplete =2;


    private EngineerService engineerService;

    @Autowired
    private ScheduleUtility scheduleUtility;

    private static Map<String, Engineer> scheduleMap;
    public ScheduleServiceImpl(){
        super();
        scheduleMap = new LinkedHashMap<String, Engineer>();
    }

    @Override
    public Map<String,Engineer> getAllSchedule(){
        for(DateAndShift dateAndShift: scheduleUtility.getDateAndShiftPool()){
            for(Engineer engineer : engineerService.getAllEngineers()){
                if(!(engineer.getNoOfDaysWorked() == shiftsToComplete) && scheduleUtility.checkEligibility(engineer,dateAndShift)){
                    scheduleMap.put(scheduleUtility.getDateAndShift(dateAndShift),engineer);
                    dateAndShift.setEngineer(engineer);
                    engineer.addOneDaysWorked();
                    break;
                }
            }
        }
        return scheduleMap;
    }

    @Override
    public void clearSchedule(){ scheduleMap.clear();}
}
