/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radek.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Radek
 */
@Entity
public class Market {
    

    private int id;
    private String name;
    private List<Company> Companies;

   

    @Id
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @OneToMany(targetEntity = Company.class, mappedBy="market", fetch = FetchType.EAGER)
     public List<Company> getCompanies() {
        return Companies;
    }

    public void setCompanies(List<Company> Companies) {
        this.Companies = Companies;
    }
    
    
    
}
