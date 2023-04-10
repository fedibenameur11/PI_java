/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.services;

import com.esprit.workshop.entites.Categorie_prod;
import com.esprit.workshop.entites.Produit;
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
public class ServiceProduit implements IService<Produit>{
    
    private Connection cnx;

    public ServiceProduit() {
        cnx = MyConnexion.getInstance().getCnx();
    }

    @Override
public void insertOne(Produit t) throws SQLException {
    String req = "INSERT INTO `produit`(`nom`, `prix`, `quantite`, `poids`) "
                + "VALUES ('" + t.getNom() + "', '" + t.getPrix() + "', '" + t.getQuantite() + "', '" + t.getPoids() + "')";
        System.out.println("vzvz"+t.getCat());
    Statement st = cnx.createStatement();
    st.executeUpdate(req);    
    System.out.println("Produit ajouté !");
}

    
    public void insertOne1(Produit t) throws SQLException {
    String req = "INSERT INTO `produit`(`nom`, `prix`, `quantite`, `poids`, `cat`) VALUES (?,?,?,?,?)";

    PreparedStatement ps = cnx.prepareStatement(req);

    ps.setString(1, t.getNom());
    ps.setDouble(2, t.getPrix());
    ps.setInt(3, t.getQuantite());
    ps.setDouble(4, t.getPoids());
    ps.setInt(5, t.getCat().getId());

    ps.executeUpdate();    
    System.out.println("Produit ajouté !");
}

    
    public void deleteOne(Produit t) throws SQLException{
        String req = "DELETE FROM `produit` WHERE nom = ? AND prix = ? AND quantite = ? AND poids = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom());   
        ps.setDouble(2,t.getPrix());
        ps.setInt(3,t.getQuantite());
        ps.setDouble(4 ,t.getPoids());
        ps.executeUpdate(); 
        System.out.println("Produit supprimé !");
    }

    @Override
    public void deleteOne(int id) throws SQLException {
        String req = "DELETE FROM `produit` WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);   
        ps.executeUpdate(); 
        System.out.println("produit supprimé !");
    }

    @Override
    public List<Produit> selectAll() throws SQLException {
        List<Produit> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `produit`";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            Produit p = new  Produit();
            
            p.setId(rs.getInt(1));
            p.setNom(rs.getString(2));
            p.setPrix(rs.getFloat(3));
            p.setQuantite(rs.getInt(4));
            p.setPoids(rs.getFloat(5));
            p.setCat((Categorie_prod) rs.getObject(6));
            
                        
            temp.add(p);
        }
        return temp;     
    }


    public void updateOne(Produit t,int id) throws SQLException {
        String req = "UPDATE `produit` SET nom=?,prix=?, quantite=?, poids=? WHERE id=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom());
        ps.setDouble(2,t.getPrix());
        ps.setInt(3,t.getQuantite());
        ps.setDouble(4,t.getPoids());       
        ps.setInt(5, id);
        ps.executeUpdate();
    System.out.println("Produit mis à jour !");
    }
    
    public void updateOne(Produit t) throws SQLException {
        String req = "UPDATE `produit` SET nom=?,prix=?, quantite=?, poids=? WHERE id=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom());
        ps.setDouble(2,t.getPrix());
        ps.setInt(3,t.getQuantite());
        ps.setDouble(4,t.getPoids());
        ps.setInt(5, t.getId());
        ps.executeUpdate();
    System.out.println("Produit mis à jour !");
    }

    
    
    
}
