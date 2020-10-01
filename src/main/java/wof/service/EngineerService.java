package wof.service;

import org.springframework.stereotype.Service;
import wof.model.Engineer;

import java.util.List;

@Service
public interface EngineerService {
    public List<Engineer> getAllEngineers();
    public void shuffleEngineers();
}
