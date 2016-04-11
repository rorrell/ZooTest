/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.zootest.data;

import com.rorrell.zootest.models.Environment;
import com.rorrell.zootest.models.Exhibit;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author rachel
 */
public interface ExhibitRepository extends CrudRepository<Exhibit, Long> {
    Exhibit findByName(String name);
    List<Exhibit> findByEnvironment(Environment env);
    List<Exhibit> findByEnvironmentId(long id);
}
