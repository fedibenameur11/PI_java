/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.services;

import edu.esprit.entities.Salle;
import edu.esprit.entities.abonnementSalle;
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
public class SalleService {
    
    private Connection cnx;

    public SalleService() {
        cnx = DataSource.getInstance().getCnx();
    }

    public void insertOne(Salle t) throws SQLException{
        String req = "INSERT INTO `salle`(`nom_salle`, `adresse_salle`, `num_telephone`, `codepostal`, `ville`, `prix_abonnement`) "
                + "VALUES ('"+t.getNom_salle()+"','"+t.getAdresse_salle()+"', '"+t.getNum_telephone()+"', '"+t.getCodepostal()+"', '"+t.getVille()+"', "+t.getPrix_abonnement()+")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);    
        System.out.println("salle ajouté !");
    }
    
    public void insertOne1(Salle t) throws SQLException{
        String req = "INSERT INTO `salle`(`nom_salle`, `adresse_salle`, `num_telephone`, `codepostal`, `ville`, `prix_abonnement`) VALUES (?,?,?,?,?,?)";
        
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setString(1, t.getNom_salle());
        ps.setString(2, t.getAdresse_salle());
        ps.setInt(3, t.getNum_telephone());
        ps.setInt(4, t.getCodepostal());
        ps.setString(5, t.getVille());
        ps.setInt(6, t.getPrix_abonnement());
        
        ps.executeUpdate(req);    
        System.out.println("salle ajouté !");
    }

    //public void updateOne(Salle t) throws SQLException{
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //String req="UPDATE salle SET `nom_salle`= ?, `adresse_salle`= ?, `num_telephone`= ?, `codepostal`= ?, `ville`= ?, `prix_abonnement`= ? WHERE id= ?";
        //PreparedStatement ps = cnx.prepareStatement(req);
        //ps.setString(1, t.getNom_salle());
        //ps.setString(2, t.getAdresse_salle());
        //ps.setInt(3, t.getNum_telephone());
        //ps.setInt(4, t.getCodepostal());
        //ps.setString(5, t.getVille());
        //ps.setInt(6, t.getPrix_abonnement());
        //ps.setInt(7, t.getId());
        //ps.executeUpdate();    
        //System.out.println("salle a été modifiée avec succés !");
    //}
    

    public void updateOne(Salle t, int id) throws SQLException {
        String req="UPDATE salle SET `nom_salle`= ?, `adresse_salle`= ?, `num_telephone`= ?, `codepostal`= ?, `ville`= ?, `prix_abonnement`= ? WHERE id="+id;
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom_salle());
        ps.setString(2, t.getAdresse_salle());
        ps.setInt(3, t.getNum_telephone());
        ps.setInt(4, t.getCodepostal());
        ps.setString(5, t.getVille());
        ps.setInt(6, t.getPrix_abonnement());
        //ps.setInt(7, t.getId());
        ps.executeUpdate();    
        System.out.println("salle a été modifiée avec succés !");
    }
    
    public void updateOne(Salle t) throws SQLException {
                String req="UPDATE salle SET `nom_salle`= ?, `adresse_salle`= ?, `num_telephone`= ?, `codepostal`= ?, `ville`= ?, `prix_abonnement`= ? WHERE nom_salle = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom_salle());
        ps.setString(2, t.getAdresse_salle());
        ps.setInt(3, t.getNum_telephone());
        ps.setInt(4, t.getCodepostal());
        ps.setString(5, t.getVille());
        ps.setInt(6, t.getPrix_abonnement());
        ps.setString(7, t.getNom_salle());
        ps.executeUpdate();
    System.out.println("salle a été modifiée avec succés !");
    }

    //@Override
    //public void deleteOne(Salle t) throws SQLException{
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    
    public void deleteOne(Salle t) throws SQLException{
        String req = "DELETE FROM `salle` WHERE nom_salle = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom_salle());   
        ps.executeUpdate(); 
        System.out.println("salle supprimée avec succés");
    }

    public void deleteOne(int id) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "Delete FROM `salle` WHERE id ='"+id+"';" ;
        //System.out.println(id);
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("salle supprimée avec succés");
    }
    
    


    public List<Salle> selectAll() throws SQLException {
        List<Salle> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `salle`";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            Salle p = new  Salle();
            
            p.setId(rs.getInt(1));
            p.setNom_salle(rs.getString(2));
            p.setAdresse_salle(rs.getString(3));
            p.setNum_telephone(rs.getInt(4));
            p.setCodepostal(rs.getInt(5));
            p.setVille(rs.getString(6));
            p.setPrix_abonnement(rs.getInt(7));
                        
            temp.add(p);
        }
        
        
        return temp;
        
    }
    
    public List<String> selectAllNames() throws SQLException {
    List<String> temp = new ArrayList<>();

    String req = "SELECT nom_salle FROM `salle`";
    Statement st = cnx.createStatement();

    ResultSet rs = st.executeQuery(req);

    while (rs.next()) {
        temp.add(rs.getString(1));
    }

    return temp;
}

    //@Override
    //public void updateOne(Salle t, int id) throws SQLException {
      //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    //}
    
    public List<Salle> selectAll1() throws SQLException {
    List<Salle> temp = new ArrayList<>();
    
    String req = "SELECT nom_salle, adresse_salle, num_telephone, codepostal, ville, prix_abonnement FROM `salle`";
    Statement st = cnx.createStatement();
    
    ResultSet rs = st.executeQuery(req);
    
    while (rs.next()) {
        Salle p = new Salle();
        
        p.setNom_salle(rs.getString(1));
        p.setAdresse_salle(rs.getString(2));
        p.setNum_telephone(rs.getInt(3));
        p.setCodepostal(rs.getInt(4));
        p.setVille(rs.getString(5));
        p.setPrix_abonnement(rs.getInt(6));
                    
        temp.add(p);
    }
    
    return temp;
}






//@Override
//public ArrayList<Salle> searchByName(String nom_salle) {
  //  ArrayList<Salle> salles = new ArrayList<>();
   // try {
     //   String req = "SELECT * FROM salle WHERE nom_salle LIKE ?";
       // PreparedStatement statement = cnx.prepareStatement(req);
       // statement.setString(1, "%" + nom_salle + "%");
       // ResultSet rs = statement.executeQuery();
       // while (rs.next()) {                
         //   Salle u = new Salle();
          //  u.setId(rs.getInt(1));
          //  u.setNom_salle(rs.getString(2));
          //  u.setAdresse_salle(rs.getString(3));
          //  u.setNum_telephone(rs.getInt(4));
          //  u.setCodepostal(rs.getInt(5));
          //  u.setVille(rs.getString(6));
          //  u.setPrix_abonnement(rs.getInt(7));
               
          //  salles.add(u);
       // }
   // } catch (SQLException ex) {
     //   Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
   // }
   // return salles;
//}

    public ArrayList<Salle> search1(String duree_abonnement) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
   }

public List<Salle> sortSalle(List<Salle> salles) {
    Collections.sort(salles, new Comparator<Salle>() {
        
        public int compare(Salle s1, Salle s2) {
            return s1.getNom_salle().compareTo(s2.getNom_salle());
        }
    });
    return salles;
} 




    public List<abonnementSalle> sortAbonnement(List<abonnementSalle> abonnements) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}

