/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.services;

import edu.esprit.entities.Question;
import edu.esprit.entities.Reponse;
import edu.esprit.util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trabe
 */
public class ServiceReponse  {
    private Connection cnx;
           public ServiceReponse() {
        cnx = DataSource.getInstance().getCnx();
           }
           
           
    
    public void insertOne(Reponse t) throws SQLException{
    String req = "INSERT INTO `reponse`(`rep`,`created_at`) "
        + "VALUES (?,?)";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setString(1, t.getContenurep());
    ps.setString(2, t.getCreated_at());
    ps.executeUpdate();   
    System.out.println("reponse ajouté !");
}

 public void insertOne1(Reponse t) throws SQLException{
    String req = "INSERT INTO `reponse`(`rep`, `created_at`,`user_id`,`question_id`,`likes`) VALUES (?,?,?,?,?)";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setString(1, t.getContenurep());
    ps.setString(2, t.getCreated_at());
    ps.setInt(3, t.getUser_id());
    ps.setInt(4, t.getQuestion_id());
    ps.setInt(5, 0);
    ps.executeUpdate();    
    System.out.println("reponse ajouté !");
}


   

    
    public void deleteOne(Reponse t) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void deleteOne(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public List<Reponse> selectAll() throws SQLException {
        List<Reponse> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `reponse`";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            Reponse r = new Reponse();
            
            r.setId(rs.getInt("id"));
            r.setContenurep(rs.getString("rep"));
            r.setCreated_at(rs.getString("created_at"));
            r.setUser_id(rs.getInt("user_id"));
            r.setQuestion_id(rs.getInt("question_id"));
            r.setLikes(rs.getInt("likes"));
           
                        
            temp.add(r);
        }
        
        
        return temp;
        
    }

    
    public void updateQuestion(Reponse t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    public void updateCount(int count, int reponseId) {

        try {
            String req = "UPDATE reponse SET `likes`= ? WHERE id=" + reponseId;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, count);
            ps.executeUpdate();
            System.out.println("Likes a été modifiée avec succés !");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReponse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    public List<Reponse> selectByQuestion(int id) throws SQLException {
        
         List<Reponse> temp = new ArrayList<>();
        
        String req = "SELECT * FROM reponse WHERE question_id="+id+" ORDER BY created_at DESC";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            Reponse r = new Reponse();
            
            r.setId(rs.getInt("id"));
            r.setContenurep(rs.getString("rep"));
            r.setCreated_at(rs.getString("created_at"));
            r.setUser_id(rs.getInt("user_id"));
            r.setQuestion_id(rs.getInt("question_id"));
            r.setLikes(rs.getInt("likes"));
            
            
           
            temp.add(r);
        }
         
        
        return temp;
        
        
    }

    
    public void deleteByQuestionId(int id) throws SQLException {
       
        String req = "DELETE FROM `reponse` WHERE `question_id`=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Tous les reponses sont supprimées !");

    }
    
}
