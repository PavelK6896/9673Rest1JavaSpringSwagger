package app.web.pavelk.rest1.controllers;


import app.web.pavelk.rest1.services.RoverS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;


@RestController
public class RoverC {

    @Autowired
    public RoverC(RoverS roverS) {
        this.roverS = roverS;
    }

    RoverS roverS;

    @RequestMapping(value = "/r1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAll(){
        return roverS.getAll();
    }
}
