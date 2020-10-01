package wof.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wof.model.Engineer;
import wof.utility.ScheduleUtility;

import java.util.List;

@Component
public class EngineerServiceImpl implements EngineerService {
    @Autowired
    private ScheduleUtility scheduleUtility;


    @Override
    public List<Engineer> getAllEngineers() {
        return scheduleUtility.getEngineersPool();
    }

    @Override
    public void shuffleEngineers() {
        scheduleUtility.shuffle();
    }
}
