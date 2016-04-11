/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.zootest.controllers;

import com.rorrell.zootest.data.AnimalRepository;
import com.rorrell.zootest.data.EnvironmentRepository;
import com.rorrell.zootest.data.ExhibitRepository;
import com.rorrell.zootest.models.Animal;
import com.rorrell.zootest.models.Environment;
import com.rorrell.zootest.models.Exhibit;
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
public class AnimalController {
    @Autowired
    private AnimalRepository animalRepo;
    
    @Autowired
    private EnvironmentRepository envRepo;
    
    @Autowired
    private ExhibitRepository exhibitRepo;
    
    @ModelAttribute("allEnvironments")
    public Iterable<Environment> getAllEnvironments() {
        return envRepo.findAll();
    }
    
    @ModelAttribute("allExhibits")
    public Iterable<Exhibit> getAllExhibits() {
        return exhibitRepo.findAll();
    }
    
    //read
    @RequestMapping("/animal/{id}") 
    public String getById(@PathVariable Long id, Model model) {
        model.addAttribute("animal", animalRepo.findOne(id));
        return "animal";
    }
    
    @RequestMapping(value="/animals", method=RequestMethod.GET)
    public String defaultList(Model model) {
        return "redirect:/animals/type";
    }
    
    //read
    @RequestMapping(value="/animals/{sort}", method=RequestMethod.GET)
    public String list(Model model, @PathVariable String sort) {
        model.addAttribute("animals", animalRepo.findAll(animalRepo.sortByPropertyAsc(sort)));
        return "animals";
    }
    
    //create
    @RequestMapping(value="/animal/add", method=RequestMethod.GET)
    public String addAnimal(Model model, Animal animal) {
        model.addAttribute("animal", animal);
        model.addAttribute("allEnvironments", envRepo.findAll());
        return "animalform";
    }
    
    //update
    @RequestMapping("/animal/edit/{id}")
    public String editAnimal(@PathVariable Long id, Model model) {
        model.addAttribute("animal", animalRepo.findOne(id));
        return "animalform";
    }
    
    //create & update
    @RequestMapping(value="/animal/save", method=RequestMethod.POST)
    public String saveAnimal(@Valid Animal animal, BindingResult result) {
        if(result.hasErrors())
            return "animalform";
        //make sure first letter of type is capitalized
        animal.setType(Character.toUpperCase(animal.getType().charAt(0)) + 
                animal.getType().substring(1));
        animalRepo.save(animal);
        return "redirect:/animals";
    }
    
    //delete
    @RequestMapping("/animal/delete/{id}")
    public String deleteAnimal(@PathVariable Long id) {
        animalRepo.delete(id);
        return "redirect:/animals";
    }
}
