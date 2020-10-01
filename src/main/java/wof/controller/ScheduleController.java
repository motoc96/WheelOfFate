package wof.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wof.model.Engineer;
import wof.service.EngineerService;
import wof.service.ScheduleService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class ScheduleController {

    private EngineerService engineerService;

    private ScheduleService scheduleService;

    @RequestMapping("/engineers")
    public List<Engineer> getAllEngineers(){
        return engineerService.getAllEngineers();
    }

    @RequestMapping("/schedules")
    public Map<String,Engineer> getAllSchedule(){
        return scheduleService.getAllSchedule();
    }

    @RequestMapping("/shuffle")
    public void shuffleSchedule(HttpServletResponse response) throws IOException{
        engineerService.shuffleEngineers();
        scheduleService.clearSchedule();
        response.sendRedirect("/schedules");
    }
}
