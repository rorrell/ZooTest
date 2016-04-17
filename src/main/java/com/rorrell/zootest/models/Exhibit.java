/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.zootest.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author rachel
 */
@Entity
public class Exhibit implements Serializable, Comparable<Exhibit> {

    private static final long serialVersionUID = 1L;
    
    @Id
//    @Column(name="id", columnDefinition="serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String location;
    @OneToMany(fetch=FetchType.LAZY, mappedBy="exhibit")
    private List<Animal> animals;
    @OneToOne
    private Environment environment;

    public Exhibit() {
    }

    public Exhibit(long id, String name, String location, Environment environment) {
        this.name = name;
        this.location = location;
        this.environment = environment;
    }
    
    public Exhibit(String name, String location, Environment environment) {
        this(0, name, location, environment);
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
    
    public void addAnimal(Animal animal) {
        if(this.animals.indexOf(animal) < 0)
            this.animals.add(animal);
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 37 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Exhibit other = (Exhibit) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Exhibit{" + "id=" + id + ", name=" + name + ", environment=" + environment + '}';
    }

    @Override
    public int compareTo(Exhibit o) {
        return this.getName().compareTo(o.getName());
    }
    
    
    
}
