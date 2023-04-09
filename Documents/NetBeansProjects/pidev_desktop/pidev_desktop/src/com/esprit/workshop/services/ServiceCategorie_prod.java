/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.services;

import com.esprit.workshop.entites.Categorie_prod;
import com.esprit.workshop.utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FGH
 */
public class ServiceCategorie_prod implements IService<Categorie_prod>{
    
    private Connection cnx;

    public ServiceCategorie_prod() {
        cnx = MyConnexion.getInstance().getCnx();
    }

    @Override
    public void insertOne(Categorie_prod t) throws SQLException{
        String req = "INSERT INTO `categorie_prod`(`nom`) "
                + "VALUES ('"+t.getNom()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);    
        System.out.println("Categorie ajouté !");
    }
    
    public void insertOne1(Categorie_prod t) throws SQLException{
        String req = "INSERT INTO `categorie_prod`(`nom`) VALUES (?)";
        
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setString(1, t.getNom());
        
        ps.executeUpdate();    
        System.out.println("Categorie ajouté !");
    }

    @Override
    public void updateOne(Categorie_prod t,int id) throws SQLException{
        String req = "UPDATE `produit` SET nom=? WHERE id=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1,t.getNom());
        ps.setInt(2, id);
        ps.executeUpdate();
    System.out.println("Categorie mis à jour !");
    }

    @Override
    public void deleteOne(Categorie_prod t) throws SQLException{
        String req = "DELETE FROM `categorie_prod` WHERE nom = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom());   
        ps.executeUpdate(); 
        System.out.println("Categorie supprimé !");
    }

    @Override
    public void deleteOne(int id) throws SQLException {
        String req = "DELETE FROM `categorie_prod` WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);   
        ps.executeUpdate(); 
        System.out.println("Categorie supprimé !");
    }

    @Override
    public List<Categorie_prod> selectAll() throws SQLException {
        List<Categorie_prod> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `categorie_prod`";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            Categorie_prod p = new  Categorie_prod();
            
            p.setId(rs.getInt(1));
            p.setNom(rs.getString(2));
                        
            temp.add(p);
        }
        
        
        return temp;
        
    }
    
    
    
}
