package wof.service;


import org.springframework.stereotype.Service;
import wof.model.Engineer;

import java.util.Map;

@Service
public interface ScheduleService {
    public Map<String, Engineer> getAllSchedule();

    public void clearSchedule();
}
