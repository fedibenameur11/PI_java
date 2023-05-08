/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Categorie_prod;
import edu.esprit.entities.Produit;
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
public class ServiceCategorie_prod {
    
    private Connection cnx;

    public ServiceCategorie_prod() {
        cnx = DataSource.getInstance().getCnx();
    }
    /*public static boolean ControleNOM(Categorie_prod u) throws SQLException {
		String str = u.getNom().toLowerCase();
                String req = "SELECT * FROM `categorie_prod` where nom="+u.getNom();
                Statement st = cnx.createStatement();    
                ResultSet rs = st.executeQuery(req);
                if (rs.next()) {System.out.println("mrigl");return true;}
                return false;
                //System.out.println(str.length());
                /*if (str.length() == 0)
                    return false;
		return true;
	}*/
    
    

    
    public void insertOne(Categorie_prod t) throws SQLException{
        String req = "INSERT INTO `categorie`(`nom`) "
                + "VALUES ('"+t.getNom()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);    
        System.out.println("Categorie ajouté !");
    }
    
    public void insertOne1(Categorie_prod t) throws SQLException{
        String req = "INSERT INTO `categorie`(`nom`) VALUES (?)";
        
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setString(1, t.getNom());
        
        ps.executeUpdate();    
        System.out.println("Categorie ajouté !");
    }

    
    public void updateOne(Categorie_prod t,int id) throws SQLException{
        String req = "UPDATE `categorie` SET nom=? WHERE id=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1,t.getNom());
        ps.setInt(2, id);
        ps.executeUpdate();
    System.out.println("Categorie mis à jour !");
    }
   
    public void updateOne(Categorie_prod t) throws SQLException{
        String req = "UPDATE `categorie` SET nom = ? WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1,t.getNom());
        ps.setInt(2, t.getId());
        ps.executeUpdate();
    System.out.println("Categorie mis à jour !");
    }


    
    public void deleteOne(Categorie_prod t) throws SQLException{
        String req = "DELETE FROM `categorie` WHERE nom = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom());   
        ps.executeUpdate(); 
        System.out.println("Categorie supprimé !");
    }

   
    public void deleteOne(int id) throws SQLException {
        String req = "DELETE FROM `categorie` WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);   
        ps.executeUpdate(); 
        System.out.println("Categorie supprimé !");
    }

    
    public List<Categorie_prod> selectAll() throws SQLException {
        List<Categorie_prod> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `categorie` ";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            Categorie_prod p = new  Categorie_prod();
            
            p.setId(rs.getInt(1));
            p.setNom(rs.getString(2));
                        
            temp.add(p);
        }
        
        
        return temp;
        
    }
    public boolean ControleNOM(Categorie_prod u) throws SQLException {
    String req = "SELECT * FROM `categorie` WHERE nom=?";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setString(1, u.getNom());
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
        System.out.println("La catégorie existe déjà.");
        return true;
    }
    return false;
}
    
    public boolean ControleNOM2(Categorie_prod p) throws SQLException {
        String req = "SELECT * FROM categorie WHERE nom LIKE ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, "%" + p.getNom() + "%");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
        System.out.println("La categorie existe déjà.");
        return true;
        }
        return false;
        }

    
    
    
}
