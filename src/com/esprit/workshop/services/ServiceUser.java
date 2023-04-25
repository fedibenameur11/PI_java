/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.services;

import com.esprit.workshop.entites.User;
import com.esprit.workshop.entites.AbonnementCoach;
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
public class ServiceUser{
    
    private Connection cnx;

    public ServiceUser() {
        cnx = MyConnexion.getInstance().getCnx();
    }
    
    
        public List<User> selectAllCoachsList() throws SQLException {
        List<User> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `user` WHERE categorie_user_id = 3";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            User p = new  User();
            p.setId(rs.getInt("id"));
            p.setCategorie_user_id(rs.getInt("categorie_user_id"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setEmail(rs.getString("email"));
            p.setTelephone(rs.getInt("telephone"));
                        
            temp.add(p);
        }    
        return temp;    
    }
    
    public User[] selectAllCoachs() throws SQLException {
        List<User> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `user` WHERE categorie_user_id = 3";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            User p = new  User();
            p.setId(rs.getInt("id"));
            p.setCategorie_user_id(rs.getInt("categorie_user_id"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setEmail(rs.getString("email"));
            p.setTelephone(rs.getInt("telephone"));
                        
            temp.add(p);
        }
        User[] array = new User[temp.size()];
        temp.toArray(array); // fill the array
        return array;    
    }
    
    public User[] selectAllClients() throws SQLException {
        List<User> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `user` WHERE categorie_user_id = 2";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            User p = new  User();
            p.setId(rs.getInt("id"));
            p.setCategorie_user_id(rs.getInt("categorie_user_id"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setEmail(rs.getString("email"));
            p.setTelephone(rs.getInt("telephone"));
                        
            temp.add(p);
        }
        User[] array = new User[temp.size()];
        temp.toArray(array); // fill the array
        return array;
    }
    
    public User getByID(int id) {
        User p = new User();
        try {
            String req = "Select * from user where id = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
            p.setId(rs.getInt("id"));
            p.setCategorie_user_id(rs.getInt("categorie_user_id"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setEmail(rs.getString("email"));
            p.setTelephone(rs.getInt("telephone"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;

    }
    
    public void ajouterAbonnement(AbonnementCoach r) {
        try {
            String req = "INSERT INTO `abonnement_coach` (`coach_id`, `client_id`, `duree_debut`, `duree_fin`, `duree_abonnement` , `statut`) VALUES (?,?,?,?,?,?)";
            
        
            PreparedStatement ps=cnx.prepareStatement(req);
             
            ps.setInt(1, r.getCoach_id().getId());
            ps.setInt(2, r.getClient_id().getId());
            ps.setDate(3, r.getDateDebut());
            ps.setDate(4, r.getDateFin());
            ps.setInt(5, r.getDuree());
            ps.setInt(6, r.getStatut());
             ps.executeUpdate();
            System.out.println("Abonnement ajouté!!!");
        } catch (SQLException ex) {
            ex.printStackTrace();                    
        }   
 }
    
}
