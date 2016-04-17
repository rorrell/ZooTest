/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.zootest.service;

import com.rorrell.zootest.data.AnimalRepository;
import com.rorrell.zootest.data.ExhibitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author rachel
 */
@Component
public class ExhibitService {
    @Autowired
    ExhibitRepository exRepo;
    
    @Autowired
    AnimalRepository animalRepo;
    
    public void safeDelete(long id) {
        exRepo.safeDeleteById(id, animalRepo);
    }
    
}
