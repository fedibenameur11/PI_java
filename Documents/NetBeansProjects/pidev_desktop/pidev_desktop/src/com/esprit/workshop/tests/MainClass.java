/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.tests;

import com.esprit.workshop.entites.Categorie_prod;
import com.esprit.workshop.entites.Produit;
import com.esprit.workshop.services.ServiceCategorie_prod;
import com.esprit.workshop.services.ServiceProduit;
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
        
        ServiceCategorie_prod sc = new ServiceCategorie_prod();
        ServiceProduit sp = new ServiceProduit();
        
        Categorie_prod c1 = new Categorie_prod("bvccaaaa");
        
        Produit p =new Produit("xnvd",311,4,56);
        Produit p2 =new Produit("xnvd",31431,14,516);
        
        try {
            //sc.insertOne(c1);
            //sp.insertOne1(p);
            //sc.deleteOne(c1);
            //sc.deleteOne(25);
            //sp.deleteOne(p);
            sp.updateOne(p2,11);
            
            System.out.println(sc.selectAll());
            //System.out.println(sp.selectAll());
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    }
    
}
