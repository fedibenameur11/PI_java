/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.users;
import edu.esprit.entities.AbonnementCoach;
import edu.esprit.util.DataSource;
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
        cnx = DataSource.getInstance().getCnx();
    }
    
    
        public List<users> selectAllCoachsList() throws SQLException {
        List<users> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `users` WHERE roles = 2";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            users p = new  users();
            p.setId(rs.getInt("id"));
            p.setCategorie_user_id(rs.getInt("roles"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setEmail(rs.getString("email"));
            p.setTelephone(rs.getInt("telephone"));
            p.setAdresse(rs.getString("adresse"));
            p.setPassword(rs.getString("password"));
            p.setCode_postale(rs.getInt("code_postale"));
                        
            temp.add(p);
        }    
        return temp;    
    }
    
    public users[] selectAllCoachs() throws SQLException {
        List<users> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `users` WHERE roles = 2";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            users p = new  users();
            p.setId(rs.getInt("id"));
            p.setCategorie_user_id(rs.getInt("roles"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setEmail(rs.getString("email"));
            p.setTelephone(rs.getInt("telephone"));
            p.setAdresse(rs.getString("adresse"));
            p.setPassword(rs.getString("password"));
            p.setCode_postale(rs.getInt("code_postale"));
                        
            temp.add(p);
        }
        users[] array = new users[temp.size()];
        temp.toArray(array); // fill the array
        return array;    
    }
    
    public users[] selectAllClients() throws SQLException {
        List<users> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `users` WHERE roles = 10";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            users p = new  users();
            p.setId(rs.getInt("id"));
            p.setCategorie_user_id(rs.getInt("roles"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setEmail(rs.getString("email"));
            p.setTelephone(rs.getInt("telephone"));
            p.setAdresse(rs.getString("adresse"));
            p.setPassword(rs.getString("password"));
            p.setCode_postale(rs.getInt("code_postale"));
                        
            temp.add(p);
        }
        users[] array = new users[temp.size()];
        temp.toArray(array); // fill the array
        return array;
    }
    
    public users getByID(int id) {
        users p = new users();
        try {
            String req = "Select * from users where id = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
            p.setId(rs.getInt("id"));
            p.setCategorie_user_id(rs.getInt("roles"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setEmail(rs.getString("email"));
            p.setTelephone(rs.getInt("telephone"));
            p.setAdresse(rs.getString("adresse"));
            p.setPassword(rs.getString("password"));
            p.setCode_postale(rs.getInt("code_postale"));
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
