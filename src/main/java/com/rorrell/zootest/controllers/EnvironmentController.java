/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.zootest.controllers;

import com.rorrell.zootest.data.EnvironmentRepository;
import com.rorrell.zootest.models.Environment;
import com.rorrell.zootest.service.EnvironmentService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rachel
 */
@Controller
public class EnvironmentController {

    @Autowired
    private EnvironmentRepository envRepo;

    @Autowired
    private EnvironmentService envSvc;

    //read
    @RequestMapping("/environment/{id}")
    public String getById(@PathVariable Long id, Model model) {
        model.addAttribute("environment", envRepo.findOne(id));
        return "environment";
    }

    @RequestMapping(value = "/environments", method = RequestMethod.GET)
    public String defaultList(Model model) {
        return "redirect:/environments/name";
    }

    //read
    @RequestMapping(value = "/environments/{sort}", method = RequestMethod.GET)
    public String list(Model model, @PathVariable String sort) {
        model.addAttribute("environments", envRepo.findAll(envRepo.sortByPropertyAsc(sort)));
        return "environments";
    }

    //create
    @RequestMapping(value = "/environment/add", method = RequestMethod.GET)
    public String addEnvironment(Model model, Environment environment) {
        model.addAttribute("environment", environment);
        model.addAttribute("allEnvironments", envRepo.findAll());
        return "environmentform";
    }

    //update
    @RequestMapping("/environment/edit/{id}")
    public String editEnvironment(@PathVariable Long id, Model model) {
        model.addAttribute("environment", envRepo.findOne(id));
        return "environmentform";
    }

    //create & update
    @RequestMapping(value = "/environment/save", method = RequestMethod.POST)
    public String saveEnvironment(@Valid Environment environment, BindingResult result) {
        if (result.hasErrors()) {
            return "environmentform";
        }
        //make sure first letter of name is capitalized
        environment.setName(Character.toUpperCase(environment.getName().charAt(0))
                + environment.getName().substring(1));
        envRepo.save(environment);
        return "redirect:/environments";
    }

    //delete
    @RequestMapping("/environment/delete/{id}")
    public String deleteEnvironment(@PathVariable Long id) {
        envSvc.safeDelete(id);
        return "redirect:/environments";
    }
}
