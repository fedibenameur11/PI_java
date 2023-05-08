/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

/**
 *
 * @author wassim
 */

import edu.esprit.entities.Commande;
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
public class CommandeService {
    
    private Connection cnx;

    public CommandeService() {
        cnx = DataSource.getInstance().getCnx();
    }


    public void insertOne(Commande t) throws SQLException{
        String req = "INSERT INTO commandes`(nom_commande`) "
                + "VALUES ('"+t.getNom_commande()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);    
        System.out.println("abonnement ajouté !");
    }
    
    public void insertOne1(Commande t) throws SQLException{
        String req = "INSERT INTO commandes`(nom_commande`,`date_commande`) VALUES (?,?)";
        
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setString(1, t.getNom_commande());
        ps.setDate(2, new java.sql.Date(t.getDate_commande().getTime()));
        
        
        ps.executeUpdate();    
        System.out.println("commande ajouté !");
    }

    public void updateOne(Commande t, int id) throws SQLException{
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req="UPDATE commandes SET nom_commande`= ?,date_commande`= ? WHERE id="+id;
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom_commande());
        ps.setDate(2, new java.sql.Date(t.getDate_commande().getTime()));
        
        //ps.setInt(2, t.getId());
        ps.executeUpdate();    
        System.out.println("commande modifiée avec succés !");
    }

    //@Override
    //public void deleteOne(Salle t) throws SQLException{
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}


    public void deleteOne(int id) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "Delete FROM commandes WHERE id ='"+id+"';";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("commande supprimé avec succés");
    }


    public List<Commande> selectAll() throws SQLException {
        List<Commande> temp = new ArrayList<>();
        
        String req = "SELECT * FROM commandes";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            Commande p1 = new  Commande();
            
            p1.setId(rs.getInt(1));
            p1.setDate_commande(rs.getDate(2));     
            p1.setNom_commande(rs.getString(3));
                 
            temp.add(p1);
        }
        
        
        return temp;
        
    }


    public List<String> selectAllNames() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}