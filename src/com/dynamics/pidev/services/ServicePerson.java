/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dynamics.pidev.services;

import com.dynamics.pidev.entites.Person;
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
 * @author FGH
 */
public class ServicePerson implements IService<Person>{
    
    private Connection cnx;

    public ServicePerson() {
        cnx = MyConnexion.getInstance().getCnx();
    }

    @Override
    public void insertOne(Person t) throws SQLException{
        String req = "INSERT INTO `person`(`nom`, `prenom`, `age`) "
                + "VALUES ('"+t.getNom()+"','"+t.getPrenom()+"', "+t.getAge()+")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);    
        System.out.println("person ajouté !");
    }
    
    public void insertOne1(Person t) throws SQLException{
        String req = "INSERT INTO `person`(`nom`, `prenom`, `age`) VALUES (?,?,?)";
        
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setString(1, t.getNom());
        ps.setString(2, t.getPrenom());
        ps.setInt(3, t.getAge());
        
        ps.executeUpdate(req);    
        System.out.println("person ajouté !");
    }

    @Override
    public void updateOne(Person t) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOne(Person t) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOne(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> selectAll() throws SQLException {
        List<Person> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `person`";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            Person p = new  Person();
            
            p.setId(rs.getInt(1));
            p.setNom(rs.getString(2));
            p.setPrenom(rs.getString("prenom"));
            p.setAge(rs.getInt("age"));
                        
            temp.add(p);
        }
        
        
        return temp;
        
    }
    
    
    
}
