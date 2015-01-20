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
public class ContactDetails {

 


private int id;
private String firstName;
private String lastName;
private String phoneNumber;
private boolean isDuplicate;

public ContactDetails(){
    
    
}
public ContactDetails(String firstName, String lastName, String phoneNumber){
    
    this.firstName=firstName;
    this.lastName=lastName;
    this.phoneNumber=phoneNumber;
    
    
}
@Id
@GenericGenerator(name="generator", strategy="increment")
@GeneratedValue(generator="generator")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isIsDuplicate() {
        return isDuplicate;
    }

    public void setIsDuplicate(boolean isDuplicate) {
        this.isDuplicate = isDuplicate;
    }
    


    
}
