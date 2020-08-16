package app.web.pavelk.rest1.services;

import app.web.pavelk.rest1.entities.Rover;
import app.web.pavelk.rest1.repositories.RoverRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoverS {

    @Autowired
    public void setRoverRepo(RoverRepo roverRepo) {
        this.roverRepo = roverRepo;
    }

    RoverRepo roverRepo;

    public List<Rover> getAll(){
        log.info("all rovers");
        return roverRepo.findAll();
    }


}
