/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase Skate
 * @author user
 */
@Entity
@Table(name="skate")
public class Skate implements Serializable{

    /**
     * id llave principal
     */
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * variable nombre
     */
    private String name;
    /**
     * variable brand
     */
    private String brand;
    /**
     * variable año
     */
    private Integer year;
    /**
     * variable descripción
     */
    private String description;
    
    /**
     * relación con la tabla category
     */
    @ManyToOne
    @JoinColumn(name = "skateId")
    @JsonIgnoreProperties("skates")
    private Category category;
    /**
     * relación con la tabla messages
     */
    @OneToMany(cascade = (CascadeType.PERSIST), mappedBy = "skate")
    @JsonIgnoreProperties({"skate","client"})
    private List<Message> messages;
    /**
     * relación con la tabla reservación
     */
    @OneToMany(cascade = (CascadeType.PERSIST), mappedBy = "skate")
    @JsonIgnoreProperties({"skate","client"})
    private List<Reservation> reservations;

    /**
     * creación de los getters and setters
     * @return 
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    
    
}
