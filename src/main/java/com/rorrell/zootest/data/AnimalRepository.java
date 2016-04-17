/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.zootest.data;

import com.rorrell.zootest.models.Animal;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rachel
 */
public interface AnimalRepository extends CrudRepository<Animal, Long>, 
        EntityRepository<Animal, Long> {

    @Modifying
    @Transactional(readOnly = false)
    @Query("update Animal as a set a.compatibleEnvironment.id=null where a.compatibleEnvironment.id=?1")
    Integer dereferenceEnvironmentById(Long id);

    @Modifying
    @Transactional(readOnly = false)
    @Query("update Animal as a set a.exhibit.id=null where a.exhibit.id=?1")
    Integer dereferenceExhibitById(Long id);
}
