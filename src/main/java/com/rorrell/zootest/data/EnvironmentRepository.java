/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.zootest.data;

import com.rorrell.zootest.models.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author rachel
 */
public interface EnvironmentRepository extends CrudRepository<Environment, Long>,
        EntityRepository<Environment, Long> {

    Environment findByName(String name);

    default void safeDeleteById(long id, AnimalRepository animalRepo, ExhibitRepository exRepo) {
        if(animalRepo.dereferenceEnvironmentById(id) > -1 && exRepo.dereferenceEnvironmentById(id) > -1)
            this.delete(id);
    }

}
