/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.zootest.service;

import com.rorrell.zootest.data.AnimalRepository;
import com.rorrell.zootest.data.EnvironmentRepository;
import com.rorrell.zootest.data.ExhibitRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author rachel
 */
public class EnvironmentService {
    @Autowired
    EnvironmentRepository envRepo;
    
    @Autowired
    AnimalRepository animalRepo;
    
    @Autowired
    ExhibitRepository exRepo;
    
    public void safeDelete(long id) {
        envRepo.safeDeleteById(id, animalRepo, exRepo);
    }
}
