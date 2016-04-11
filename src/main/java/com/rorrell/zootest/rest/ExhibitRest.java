/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.zootest.rest;

import com.rorrell.zootest.data.ExhibitRepository;
import com.rorrell.zootest.models.Exhibit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rachel
 */
@RestController
public class ExhibitRest {
    
    @Autowired
    private ExhibitRepository exhibitRepo;
    
    @RequestMapping(value="/exhibits-by-environment", method=RequestMethod.GET)
    public List<Exhibit> getExhibitsByEnvironment(@RequestParam("env") long envId) {
        return exhibitRepo.findByEnvironmentId(envId);
    }
}
