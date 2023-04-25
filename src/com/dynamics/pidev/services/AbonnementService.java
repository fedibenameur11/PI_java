/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dynamics.pidev.services;

import com.dynamics.pidev.entites.abonnementSalle;
import com.dynamics.pidev.utils.MyConnexion;
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
public class AbonnementService implements IService<abonnementSalle>{
    
    private Connection cnx;

    public AbonnementService() {
        cnx = MyConnexion.getInstance().getCnx();
    }

    @Override
    public void insertOne(abonnementSalle t) throws SQLException{
        String req = "INSERT INTO `commandes`(`nom_commande`) "
                + "VALUES ('"+t.getNom_commande()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);    
        System.out.println("abonnement ajouté !");
    }
    
    public void insertOne1(abonnementSalle t) throws SQLException{
        String req = "INSERT INTO `commandes`(`nom_commande`) VALUES (?)";
        
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setString(1, t.getNom_commande());
        
        
        ps.executeUpdate();    
        System.out.println("abonnement ajouté !");
    }

    public void updateOne(abonnementSalle t, int id) throws SQLException{
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req="UPDATE commandes SET `nom_commande`= ? WHERE id="+id;
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom_commande());
        
        //ps.setInt(2, t.getId());
        ps.executeUpdate();    
        System.out.println("abonnement modifié avec succés !");
    }

    //@Override
    //public void deleteOne(Salle t) throws SQLException{
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

    @Override
    public void deleteOne(int id) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "Delete FROM `commandes` WHERE id ='"+id+"';";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("abonnement supprimé avec succés");
    }

    @Override
    public List<abonnementSalle> selectAll() throws SQLException {
        List<abonnementSalle> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `commandes`";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            abonnementSalle p1 = new  abonnementSalle();
            
            p1.setId(rs.getInt(1));
            
            p1.setNom_commande(rs.getString(2));
                        
            temp.add(p1);
        }
        
        
        return temp;
        
    }

    @Override
    public List<String> selectAllNames() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}

