/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radek.handlers;

import com.radek.model.Contact;
import com.radek.utils.HibernateUtil;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Radek
 */
public class ContactHandler {
 
    private HibernateUtil helper;
    private SessionFactory factory;
    private Session session;
    private List<Contact> duplicatedContactsList;
    private List<Contact> contactsList;
    
    public ContactHandler(){
        
        factory = (SessionFactory) helper.getSessionFactory();
        session = factory.openSession();
        Criteria cr = session.createCriteria(Contact.class);
        contactsList= cr.list();
        session.close();
        
    }
    
    // metod which processes Contact List to get duplicated contacts
    public List<Contact> getDuplicatedContacts(List<Contact> contactList){
        
        this.contactsList = contactList;
        List<Contact> result = new ArrayList<Contact>();
        
        List<String> duplicatedPhoneNumbers = getDuplicatedPhoneNumbers();
        List<Contact> duplicatedContactsList = new ArrayList<Contact>();
        
        for (String duplicatedPhoneNumber: duplicatedPhoneNumbers ){
            List<Contact> tempContactList= new ArrayList<Contact>();
            for(Contact contact: contactList){
                
                
                if(contact.getContactDetails().getPhoneNumber().equals(duplicatedPhoneNumber)){
                    tempContactList.add(contact);
                    System.out.println("DUPLICATE: "+contact.getContactDetails().getPhoneNumber() +" id "+contact.getContactDetails().getId());
                }
            }
            for (Contact contact: filterContactsList(tempContactList)){
                result.add(contact);
                System.out.println("LOOSER"+contact.getContactDetails().getId());
             }; 
             
             
            System.out.println("=========NEXT PHONE NUMBER========");
            
            
        }
        
        this.duplicatedContactsList = result;
        return this.duplicatedContactsList;
    }
    
    //method which filters contacts and returns duplicates
    public List<Contact> filterContactsList(List<Contact> contactList){
        
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        List<Contact> result = new ArrayList<Contact>();

        for(Contact contact: contactList){
            result.add(contact);
        }

        List<Date> createDateList = new ArrayList<Date>();
        List<Date> lastContactDateList = new ArrayList<Date>();

        for (Contact contact: contactList){
            if(contact.getLastContactDate()!= null){
                
                lastContactDateList.add((Date) contact.getLastContactDate());
            }
            if(contact.getCreateDate()!= null){
                createDateList.add((Date) contact.getCreateDate());

            }
            
        }
        
        //scenario when there is only one result with most recent LastContactDate
        if (lastContactDateList.size() == 1 ){
            
            for(Contact contact: contactList){
                
                if(df.format(contact.getLastContactDate()).equals(df.format(Collections.max(lastContactDateList)))){
                    
                    result.remove(contact);
                }

            }
        }
        //scenario when there is more than one results with most recent LastContactDate
        else if(lastContactDateList.size() > 1){
            
            List<Contact> temporaryContacts=new ArrayList<Contact>();
            List<Date> temporaryCreateDateList=new ArrayList<Date>();
            
            for(Contact contact: contactList){
                
                if(contact.getLastContactDate()!=null){
                if(df.format(contact.getLastContactDate()).equals(df.format(Collections.max(lastContactDateList)))){
                 temporaryContacts.add(contact);
                 temporaryCreateDateList.add((Date) contact.getCreateDate());
                  
                } 
                }
            }
            for (Contact contact: temporaryContacts){
                if(df.format(contact.getCreateDate()).equals(df.format(Collections.min(temporaryCreateDateList)))){
                    
                    result.remove(contact);
                }

            
            }   
            //scenario when there is no contact with last contact date
        }else if (lastContactDateList.size()==0){
                createDateList.clear();
                for (Contact contact: contactList){
                    if (contact.getCreateDate() != null){

                    createDateList.add((Date) contact.getCreateDate());
                    }
                }
            
            if (createDateList.size() >0 ){
            
            for (Contact contact: contactList){
            
            if (df.format(contact.getCreateDate()).equals(df.format(Collections.min(createDateList)))){

             result.remove(contact);  
             
            }
        }
        
        
        }
            
        }

        return result;
}

    
    //method which decides which phoneNumbers apperas in databse more than once
    public List<String> getDuplicatedPhoneNumbers(){
        
        List<Contact> contacts = this.contactsList;
        ArrayList<String> phoneNumberEncounter = new ArrayList<String>();
        ArrayList<Integer> numberEncounter = new ArrayList<Integer>();
        ArrayList<String> result = new ArrayList<String>();
        
        for(int i = 0; i < contacts.size(); i++){
        
            String phoneNumber = contacts.get(i).getContactDetails().getPhoneNumber();
            
            if (phoneNumberEncounter.contains(phoneNumber)){
                
                int position = phoneNumberEncounter.indexOf(phoneNumber);
                Integer number = numberEncounter.get(position);
                int number_int=number.intValue();
                number_int++;
                number = new Integer(number_int);
                numberEncounter.set(position, number);
            } else {
                phoneNumberEncounter.add(phoneNumber);
                numberEncounter.add(new Integer(1));
            }
        }
         
        for(int i =0; i < phoneNumberEncounter.size(); i++){
            
            if (numberEncounter.get(i) > 1){
                
                result.add(phoneNumberEncounter.get(i));
            }
            System.out.println(i+"\t"+phoneNumberEncounter.get(i)+"\t"+numberEncounter.get(i));
        }
         return result; 
        
    }
    
    
        public List<Contact> getDuplicatedContactsList(){
        
        return this.duplicatedContactsList;
    }
    
        public void setDuplicatedContactsList(List<Contact> contactList){
        
        this.duplicatedContactsList=contactList;
    }

        public List<Contact> getContactsList() {
        return contactsList;
        }

        public void setContactsList(List<Contact> contactsList) {
        this.contactsList = contactsList;
        }
        
        
}
