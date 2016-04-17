/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.zootest.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author rachel
 */
@Entity
public class Environment implements Serializable, Comparable<Environment> {

    private static final long serialVersionUID = 1L;
    
    @Id
//    @Column(name="id", columnDefinition="serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @NotNull
    @NotBlank
    private String name;
    @OneToMany(fetch=FetchType.LAZY, mappedBy="compatibleEnvironment")
    private List<Animal> compatibleAnimals;

    public Environment() {
    }

    public Environment(String name) {
        this.name = name;
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

    public void setName(String name) {
        this.name = name;
    }

    public List<Animal> getCompatibleAnimals() {
        return compatibleAnimals;
    }

    public void setCompatibleAnimals(List<Animal> compatibleAnimals) {
        this.compatibleAnimals = compatibleAnimals;
    }
    
    public void addCompatibleAnimal(Animal animal) {
        if(this.compatibleAnimals.indexOf(animal) < 0)
            this.compatibleAnimals.add(animal);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 97 * hash + Objects.hashCode(this.name);
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
        final Environment other = (Environment) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Environment{" + "id=" + id + ", name=" + name + '}';
    }

    @Override
    public int compareTo(Environment o) {
        return this.getName().compareTo(o.getName());
    }
    
    
    
}
