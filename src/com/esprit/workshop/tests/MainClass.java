/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.tests;

import com.esprit.workshop.entites.Person;
import com.esprit.workshop.services.ServicePerson;
import com.esprit.workshop.utils.MyConnexion;
import java.sql.SQLException;

/**
 *
 * @author FGH
 */
public class MainClass {
    
    public static void main(String[] args) {
        MyConnexion cn1 = MyConnexion.getInstance();
        MyConnexion cn2 = MyConnexion.getInstance();
        MyConnexion cn3 = MyConnexion.getInstance();
        MyConnexion cn4 = MyConnexion.getInstance();
        MyConnexion cn5 = MyConnexion.getInstance();
        
        System.out.println(cn1.hashCode());
        System.out.println(cn2.hashCode());
        System.out.println(cn3.hashCode());
        System.out.println(cn4.hashCode());
        System.out.println(cn5.hashCode());
        
        ServicePerson sp = new ServicePerson();
        
        Person p = new Person(0, "Chaouachi", "Mohamed", 22);
        
        try {
            sp.insertOne(p);
            
            System.out.println(sp.selectAll());
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    }
    
}
