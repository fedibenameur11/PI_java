/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.services;

import com.esprit.workshop.entites.Person;
import com.esprit.workshop.entites.AbonnementCoach;
import com.esprit.workshop.entites.User;
import com.esprit.workshop.utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author FGH
 */
public class ServiceAbonnementCoach implements IService<AbonnementCoach>{
    
    private Connection cnx;

    public ServiceAbonnementCoach() {
        cnx = MyConnexion.getInstance().getCnx();
    }
    
    @Override
    public void insertOne(AbonnementCoach t) throws SQLException{
        /*String req = "INSERT INTO `person`(`coach_id`, `client_id`, `duree_debut`, `duree_fin`, `duree_abonnement`, `statut`) "
                + "VALUES ('"+t.getClient_id()+"','"+t.getCoach_id()+"', "+t.getAge()+")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);    
        System.out.println("person ajouté !");*/
    }
    
    public void insertOne1(AbonnementCoach t) throws SQLException{
        String req = "INSERT INTO `abonnement_coach`(`coach_id`, `client_id`, `duree_debut`, `duree_fin`, `duree_abonnement`, `statut`) VALUES (?,?,?,?,?,?)";
        
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setInt(1, t.getCoach_id().getId());
        ps.setInt(2, t.getClient_id().getId());
        ps.setDate(3, t.getDateDebut());
        ps.setDate(4, t.getDateFin());
        ps.setInt(5, t.getDuree());
        ps.setInt(6, t.getStatut());
        
        ps.executeUpdate();    
        System.out.println("Abonnement Coach ajouté !");
    }

    @Override
    public void updateOne(AbonnementCoach t) throws SQLException{
       String req = "UPDATE abonnement_coach set coach_id = ?, client_id = ? ,duree_debut= ? ,duree_fin= ? ,duree_abonnement= ? ,statut= ? where id = ? ";
       
       PreparedStatement ps = cnx.prepareStatement(req);
       ps.setInt(1, t.getCoach_id().getId());
       ps.setInt(2, t.getClient_id().getId());
       ps.setDate(3, t.getDateDebut());
       ps.setDate(4, t.getDateFin());
       ps.setInt(5, t.getDuree());
       ps.setInt(6, t.getStatut());
       ps.setInt(7, t.getId());
       
       ps.executeUpdate();
       System.out.println("Abonnement Coach mis à jour !");
    }

    @Override
    public void deleteOne(AbonnementCoach t) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOne(int id) throws SQLException {
        String req = "DELETE from abonnement_coach where id = ?";
        
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        
        ps.executeUpdate();
        System.out.println("Abonnement Coach supprimé !");
    }

    @Override
    public ObservableList <AbonnementCoach> selectAll() throws SQLException {
        ObservableList <AbonnementCoach> temp = FXCollections.observableArrayList();
        
        String req = "SELECT * FROM `abonnement_coach`";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            User u01 = null;
            User u02 = null;
            
            String req01 = "SELECT * FROM `user` WHERE `id` = " + rs.getInt("coach_id")+ " LIMIT 1;" ;
            String req02 = "SELECT * FROM `user` WHERE id = " + rs.getInt("client_id") + " LIMIT 1;";
            
            Statement st1 = cnx.createStatement();            
            ResultSet rs01 = st1.executeQuery(req01);
            
            Statement st2 = cnx.createStatement();
            ResultSet rs02 = st2.executeQuery(req02);
            
            while (rs01.next()) {
                u01 = new User(rs01.getInt("id"),
                        rs01.getInt("categorie_user_id"),
                        rs01.getInt("telephone"),
                        rs01.getString("nom"),
                        rs01.getString("prenom"),
                        rs01.getString("email"));
            }
            while (rs02.next()) {
                u02 = new User(rs02.getInt("id"),
                        rs02.getInt("categorie_user_id"),
                        rs02.getInt("telephone"),
                        rs02.getString("nom"),
                        rs02.getString("prenom"),
                        rs02.getString("email"));
                
            }
            
            AbonnementCoach p = new  AbonnementCoach(
                    rs.getInt("id"),
                    u01,u02,
                    rs.getInt("duree_abonnement"),
                    rs.getDate("duree_debut"),
                    rs.getDate("duree_fin"),
                    rs.getInt("statut")
            );
           
                        
            temp.add(p);
        }
        
        
        return temp;
        
    }

    public List<AbonnementCoach> selectAbonnementByCoch(int id,int stat) throws SQLException {
        List<AbonnementCoach> temp = new ArrayList<>();        
        String req = "SELECT * FROM `abonnement_coach` where coach_id = " + id + " and statut = "+stat ;
        System.out.println(req);
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            User u01 = null;
            User u02 = null;
            
            String req01 = "SELECT * FROM `user` WHERE `id` = " + rs.getInt("coach_id")+ " LIMIT 1;" ;
            String req02 = "SELECT * FROM `user` WHERE id = " + rs.getInt("client_id") + " LIMIT 1;";
            
            Statement st1 = cnx.createStatement();            
            ResultSet rs01 = st1.executeQuery(req01);
            
            Statement st2 = cnx.createStatement();
            ResultSet rs02 = st2.executeQuery(req02);
            
            while (rs01.next()) {
                u01 = new User(rs01.getInt("id"),
                        rs01.getInt("categorie_user_id"),
                        rs01.getInt("telephone"),
                        rs01.getString("nom"),
                        rs01.getString("prenom"),
                        rs01.getString("email"));
            }
            while (rs02.next()) {
                u02 = new User(rs02.getInt("id"),
                        rs02.getInt("categorie_user_id"),
                        rs02.getInt("telephone"),
                        rs02.getString("nom"),
                        rs02.getString("prenom"),
                        rs02.getString("email"));
                
            }
            
            AbonnementCoach p = new  AbonnementCoach(
                    rs.getInt("id"),
                    u01,u02,
                    rs.getInt("duree_abonnement"),
                    rs.getDate("duree_debut"),
                    rs.getDate("duree_fin"),
                    rs.getInt("statut")
            );
           
                        
            temp.add(p);
        }
        System.out.println(temp);
        return temp;
        
    }
    
     
     
      public AbonnementCoach getByID(int id) {
        AbonnementCoach  p=null ;
        ServiceUser us = new  ServiceUser() ; 

        try {
            String req = "Select * from abonnement_coach where id = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
             p =  new AbonnementCoach(id, us.getByID(rs.getInt("coach_id")),us.getByID(rs.getInt("client_id")), rs.getInt("duree_abonnement"), rs.getDate("duree_debut"), rs.getDate("duree_fin"), rs.getInt("statut"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p ;

    }
    
    
}
