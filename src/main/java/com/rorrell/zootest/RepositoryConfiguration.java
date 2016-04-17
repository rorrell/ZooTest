/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.zootest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author rachel
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.rorrell.zootest.models"})
@EnableJpaRepositories(basePackages = {"com.rorrell.zootest.data"})
@EnableTransactionManagement
public class RepositoryConfiguration {
    
}
