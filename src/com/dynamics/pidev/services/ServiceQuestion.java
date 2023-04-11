/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dynamics.pidev.services;

import com.dynamics.pidev.entites.Person;
import com.dynamics.pidev.entites.Question;
import com.dynamics.pidev.workshop.utils.MyConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trabe
 */
public class ServiceQuestion implements IService<Question> {
        private Connection cnx;
           public ServiceQuestion() {
        cnx = MyConnexion.getInstance().getCnx();
           }
           
           
    @Override
public void insertOne(Question t) throws SQLException{
    String req = "INSERT INTO `question`(`contenu`,`created_at`) "
        + "VALUES (?,?)";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setString(1, t.getContenu());
    ps.setString(2, t.getCreated_at());
    ps.executeUpdate();   
    System.out.println("question ajouté !");
}

public void insertOne1(Question t) throws SQLException{
    String req = "INSERT INTO `question`(`contenu`, `created_at`) VALUES (?,?)";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setString(1, t.getContenu());
    ps.setString(2, "2024-03-11 00:22:44");
    ps.executeUpdate();    
    System.out.println("question ajouté !");
}


    @Override
    public void updateOne(Question t) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOne(Question t) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOne(int id) throws SQLException {
    //String req = "DELETE FROM `question` WHERE `id`=?";
    //PreparedStatement ps = cnx.prepareStatement(req);
    //ps.setInt(1, question.getId());
    //ps.executeUpdate();
   // System.out.println("question supprimée !");

    }

    @Override
    public List<Question> selectAll() throws SQLException {
        List<Question> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `question`";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            Question q = new  Question();
            
            q.setId(rs.getInt(1));
            q.setContenu(rs.getString("contenu"));
            q.setCreated_at(rs.getString("created_at"));
           
                        
            temp.add(q);
        }
        
        
        return temp;
        
    }
    
    
    
}

    

