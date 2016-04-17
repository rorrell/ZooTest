/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.zootest.data;

import com.rorrell.zootest.models.Environment;
import com.rorrell.zootest.models.Exhibit;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rachel
 */
public interface ExhibitRepository extends CrudRepository<Exhibit, Long>,
        EntityRepository<Exhibit, Long> {
    Exhibit findByName(String name);
    List<Exhibit> findByEnvironment(Environment env);
    List<Exhibit> findByEnvironmentId(long id);
    
    @Modifying
    @Transactional(readOnly = false)
    @Query("update Exhibit as e set e.environment.id=null where e.environment.id=?1")
    Integer dereferenceEnvironmentById(Long id);

    default void safeDeleteById(long id, AnimalRepository animalRepo) {
        if(animalRepo.dereferenceExhibitById(id) > -1)
            this.delete(id);
    }
}
