/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.services;

import edu.esprit.entities.abonnementSalle;
import edu.esprit.entities.Salle;
import edu.esprit.util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FGH
 */
public class AbonnementService {
    
    private Connection cnx;

    public AbonnementService() {
        cnx = DataSource.getInstance().getCnx();
    }

    public void insertOne(abonnementSalle t) throws SQLException{
        String req = "INSERT INTO `abonnement_salle`(`duree_abonnement`) "
                + "VALUES ('"+t.getDuree_abonnement()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);    
        System.out.println("abonnement ajouté !");
    }
    
    public void insertOne1(abonnementSalle t) throws SQLException{
        String req = "INSERT INTO `abonnement_salle`(`duree_abonnement`) VALUES (?)";
        
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setString(1, t.getDuree_abonnement());
        
        
        ps.executeUpdate();    
        System.out.println("abonnement ajouté !");
    }

    public void updateOne(abonnementSalle t, int id) throws SQLException{
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req="UPDATE abonnement_salle SET `duree_abonnement`= ? WHERE id="+id;
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getDuree_abonnement());
        
        //ps.setInt(2, t.getId());
        ps.executeUpdate();    
        System.out.println("abonnement modifié avec succés !");
    }

    //@Override
    //public void deleteOne(Salle t) throws SQLException{
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    
    public void deleteOne(abonnementSalle t) throws SQLException{
        String req = "DELETE FROM `abonnement_salle` WHERE durre_abonnement = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getDuree_abonnement());   
        ps.executeUpdate(); 
        System.out.println("Categorie supprimé !");
    }

    public void deleteOne(int id) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "Delete FROM `abonnement_salle` WHERE id ='"+id+"';";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("abonnement supprimé avec succés");
    }


    public List<abonnementSalle> selectAll() throws SQLException {
        List<abonnementSalle> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `abonnement_salle`";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            abonnementSalle p1 = new  abonnementSalle();
            
            p1.setId(rs.getInt(1));
            
            p1.setDuree_abonnement(rs.getString(2));
                        
            temp.add(p1);
        }
        
        
        return temp;
        
    }


    public List<String> selectAllNames() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
 

    
    public ArrayList<abonnementSalle> search1(String duree_abonnement) {
        ArrayList<abonnementSalle> abonnements = new ArrayList<>();
    try {
        String req = "SELECT * FROM abonnement_salle WHERE duree_abonnement LIKE ?";
        PreparedStatement statement = cnx.prepareStatement(req);
        statement.setString(1, "%" + duree_abonnement + "%");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {                
            abonnementSalle u = new abonnementSalle();
            u.setId(rs.getInt(1));
            u.setDuree_abonnement(rs.getString(2));
            
               
            abonnements.add(u);
        }
    } catch (SQLException ex) {
        Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return abonnements;
}

  //  @Override
  //  public ArrayList<abonnementSalle> searchByName(String nom_salle) {
   //     throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    //}

   
    public List<Salle> sortSalle(List<Salle> salles) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public List<abonnementSalle> sortAbonnement(List<abonnementSalle> abonnements) {
       Collections.sort(abonnements, new Comparator<abonnementSalle>() {
        public int compare(abonnementSalle a1, abonnementSalle a2) {
            return a1.getDuree_abonnement().compareTo(a2.getDuree_abonnement());
        }
    });
    return abonnements;
    }
    
 


    }


