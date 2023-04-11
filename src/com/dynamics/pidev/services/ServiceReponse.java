/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dynamics.pidev.services;

import com.dynamics.pidev.entites.Question;
import com.dynamics.pidev.entites.Reponse;
import com.dynamics.pidev.workshop.utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trabe
 */
public class ServiceReponse implements IService<Reponse> {
    private Connection cnx;
           public ServiceReponse() {
        cnx = MyConnexion.getInstance().getCnx();
           }
           
           
    @Override
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
    String req = "INSERT INTO `reponse`(`rep`, `created_at`) VALUES (?,?)";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setString(1, t.getContenurep());
    ps.setString(2, "024-05-11 05:22:44");
    ps.executeUpdate();    
    System.out.println("reponse ajouté !");
}


    @Override
    public void updateOne(Reponse t) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOne(Reponse t) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOne(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reponse> selectAll() throws SQLException {
        List<Reponse> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `question`";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            Reponse r = new Reponse();
            
            r.setId(rs.getInt(1));
            r.setContenurep(rs.getString("contenu"));
            r.setCreated_at(rs.getString("created_at"));
           
                        
            temp.add(r);
        }
        
        
        return temp;
        
    }
    
}
