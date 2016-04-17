/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.zootest.controllers;

import com.rorrell.zootest.data.EnvironmentRepository;
import com.rorrell.zootest.data.ExhibitRepository;
import com.rorrell.zootest.models.Environment;
import com.rorrell.zootest.models.Exhibit;
import com.rorrell.zootest.service.ExhibitService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rachel
 */
@Controller
public class ExhibitController {

    @Autowired
    private ExhibitRepository exRepo;

    @Autowired
    private ExhibitService exSvc;
    
    @Autowired
    private EnvironmentRepository envRepo;
    
    @ModelAttribute("allEnvironments")
    public Iterable<Environment> getAllEnvironments() {
        return envRepo.findAll();
    }

    //read
    @RequestMapping("/exhibit/{id}")
    public String getById(@PathVariable Long id, Model model) {
        model.addAttribute("exhibit", exRepo.findOne(id));
        return "exhibit";
    }

    @RequestMapping(value = "/exhibits", method = RequestMethod.GET)
    public String defaultList(Model model) {
        return "redirect:/exhibits/name";
    }

    //read
    @RequestMapping(value = "/exhibits/{sort}", method = RequestMethod.GET)
    public String list(Model model, @PathVariable String sort) {
        model.addAttribute("exhibits", exRepo.findAll(exRepo.sortByPropertyAsc(sort)));
        return "exhibits";
    }

    //create
    @RequestMapping(value = "/exhibit/add", method = RequestMethod.GET)
    public String addExhibit(Model model, Exhibit exhibit) {
        model.addAttribute("exhibit", exhibit);
        model.addAttribute("allExhibits", exRepo.findAll());
        return "exhibitform";
    }

    //update
    @RequestMapping("/exhibit/edit/{id}")
    public String editExhibit(@PathVariable Long id, Model model) {
        model.addAttribute("exhibit", exRepo.findOne(id));
        return "exhibitform";
    }

    //create & update
    @RequestMapping(value = "/exhibit/save", method = RequestMethod.POST)
    public String saveExhibit(@Valid Exhibit exhibit, BindingResult result) {
        if (result.hasErrors()) {
            return "exhibitform";
        }
        //make sure first letter of name is capitalized
        exhibit.setName(Character.toUpperCase(exhibit.getName().charAt(0))
                + exhibit.getName().substring(1));
        exRepo.save(exhibit);
        return "redirect:/exhibits";
    }

    //delete
    @RequestMapping("/exhibit/delete/{id}")
    public String deleteExhibit(@PathVariable Long id) {
        exSvc.safeDelete(id);
        return "redirect:/exhibits";
    }
}
