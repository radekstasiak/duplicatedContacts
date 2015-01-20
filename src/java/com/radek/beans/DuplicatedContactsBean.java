/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radek.beans;


import com.radek.handlers.ContactHandler;
import com.radek.model.ContactDetails;
import com.radek.model.Company;
import com.radek.model.Contact;
import com.radek.utils.HibernateUtil;
import com.radek.model.Market;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Radek
 */
@ManagedBean
@SessionScoped
public class DuplicatedContactsBean {
    

    private ContactHandler contactHandler;
    private List<Contact> duplicatedContactsList;
    private List<Contact> contactsList;
    
    
    public DuplicatedContactsBean(){
        
        
        ContactHandler ctHandl = new ContactHandler();
        
        this.contactsList = ctHandl.getContactsList();
        this.contactHandler = ctHandl;
        
        
        
    }
       
    public List<Contact> getDuplicatedContactsList(){
        List<Contact> ctList = new ArrayList<Contact>();
        ContactHandler ctHandl = new ContactHandler();
        ctList = this.contactsList;
        this.duplicatedContactsList = ctHandl.getDuplicatedContacts(ctList);
        
        return duplicatedContactsList;
        
    }
}