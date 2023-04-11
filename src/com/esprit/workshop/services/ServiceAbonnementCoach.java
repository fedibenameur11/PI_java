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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOne(AbonnementCoach t) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOne(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList <AbonnementCoach> selectAll() throws SQLException {
        ObservableList <AbonnementCoach> temp = FXCollections.observableArrayList();
        
        String req = "SELECT * FROM `abonnement_coach`";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            User u01 = new  User();
            User u02 = new  User();
            
            String req01 = "SELECT * FROM `user` WHERE id = " + rs.getInt("coach_id") + "LIMIT 1";
            String req02 = "SELECT * FROM `user` WHERE id = " + rs.getInt("client_id") + "LIMIT 1";
            ResultSet rs01 = st.executeQuery(req01);
            ResultSet rs02 = st.executeQuery(req02);
            
            u01.setId(rs01.getInt("id"));
            u01.setCategorie_user_id(rs01.getInt("categorie_user_id"));
            u01.setNom(rs01.getString("nom"));
            u01.setPrenom(rs01.getString("prenom"));
            u01.setEmail(rs01.getString("email"));
            u01.setTelephone(rs01.getInt("telephone"));
            
            u02.setId(rs02.getInt("id"));
            u02.setCategorie_user_id(rs02.getInt("categorie_user_id"));
            u02.setNom(rs02.getString("nom"));
            u02.setPrenom(rs02.getString("prenom"));
            u02.setEmail(rs02.getString("email"));
            u02.setTelephone(rs02.getInt("telephone"));
            
            
            AbonnementCoach p = new  AbonnementCoach();
            p.setCoach_id(u01);
            p.setClient_id(u02);
            p.setDateDebut(rs.getDate("duree_debut"));
            p.setDateFin(rs.getDate("duree_fin"));
            p.setDuree(rs.getInt("duree_abonnement"));
            p.setStatut(rs.getInt("statut"));
                        
            temp.add(p);
        }
        
        
        return temp;
        
    }
    
    
    
}
