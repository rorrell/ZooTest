/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.zootest.models;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author rachel
 */
@Entity
public class Animal implements Serializable, Comparable<Animal> {

    private static final long serialVersionUID = 1L;
    
    @Id
//    @Column(name="id", columnDefinition="serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Min(1)
    private int quantity;
    @NotNull
    @NotBlank
    private String type;
    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JoinColumn(nullable=true)
    private Exhibit exhibit;
    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JoinColumn(nullable=true)
    private Environment compatibleEnvironment;

    public Animal() {
        this.exhibit = new Exhibit();
        this.compatibleEnvironment = new Environment();
    }

    public Animal(String type, int quantity, Exhibit exhibit, Environment compatibleEnvironment) {
        this.type = type;
        this.quantity = quantity;
        this.exhibit = exhibit;
        this.compatibleEnvironment = compatibleEnvironment;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Exhibit getExhibit() {
        return exhibit == null ? new Exhibit() : exhibit;
    }

    public void setExhibit(Exhibit exhibit) {
        this.exhibit = exhibit;
    }

    public Environment getCompatibleEnvironment() {
        return compatibleEnvironment == null ? new Environment() : compatibleEnvironment;
    }

    public void setCompatibleEnvironment(Environment compatibleEnvironment) {
        this.compatibleEnvironment = compatibleEnvironment;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + Objects.hashCode(this.type);
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
        final Animal other = (Animal) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", type=" + type + ", exhibit=" + exhibit + ", compatibleEnvironment=" + compatibleEnvironment + '}';
    }

    @Override
    public int compareTo(Animal o) {
        return Long.valueOf(this.getId()).compareTo(o.getId());
    }
    
    
    
}
