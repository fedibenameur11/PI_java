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
import edu.esprit.entities.Livraison;
import edu.esprit.util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class LivraisonService implements Services<Livraison>{
    
    private Connection cnx;

    public LivraisonService() {
        cnx = DataSource.getInstance().getCnx();
    }

    @Override
    public void insertOne(Livraison t) throws SQLException{
        String req = "INSERT INTO `livraison`(`nom_livraison`, `date`, `destination`) "
                + "VALUES ('"+t.getNom_livraison()+"','"+t.getDate()+"', '"+t.getDestination()+")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);    
        System.out.println("salle ajouté !");
    }
    
   public void insertOne1(Livraison t) throws SQLException {
    String req = "INSERT INTO `livraison`(`nom_livraison`, `date`, `destination`) VALUES (?,?,?)";

    PreparedStatement ps = cnx.prepareStatement(req);

    ps.setString(1, t.getNom_livraison());
    ps.setString(2, t.getDate());
    ps.setString(3, t.getDestination());

    ps.executeUpdate();    
    System.out.println("Salle ajoutée !");
}

    //public void updateOne(Livraison t) throws SQLException{
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
    
    @Override
    public void updateOne(Livraison t, int id) throws SQLException {
        String req="UPDATE livraison SET `nom_livraison`= ?, `date`= ?, `destination`= ? WHERE id_livraison="+id;
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom_livraison());
        ps.setString(2, t.getDate());
        ps.setString(3, t.getDestination());
        //ps.setInt(7, t.getId());
        ps.executeUpdate();    
        System.out.println("salle a été modifiée avec succés !");
    }

    //@Override
    //public void deleteOne(Livraison t) throws SQLException{
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

    @Override
    public void deleteOne(int id) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "Delete FROM `livraison` WHERE id_livraison ='"+id+"';";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("salle supprimée avec succés");
    }

    @Override
    public List<Livraison> selectAll() throws SQLException {
        List<Livraison> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `livraison`";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            Livraison p = new  Livraison();
            
            p.setId_livraison(rs.getInt(1));
            p.setNom_livraison(rs.getString(2));
            p.setDate(rs.getString(3));
            p.setDestination(rs.getString(4));
          
                        
            temp.add(p);
        }
        
        
        return temp;
        
    }
    
    public List<String> selectAllNames() throws SQLException {
    List<String> temp = new ArrayList<>();

    String req = "SELECT nom_livraison FROM `livraison`";
    Statement st = cnx.createStatement();

    ResultSet rs = st.executeQuery(req);

    while (rs.next()) {
        temp.add(rs.getString(1));
    }

    return temp;
}

    //@Override
    //public void updateOne(Livraison t, int id) throws SQLException {
      //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    //}
    
    public List<Livraison> selectAll1() throws SQLException {
    List<Livraison> temp = new ArrayList<>();
    
    String req = "SELECT nom_livraison, date, destination FROM `livraison`";
    Statement st = cnx.createStatement();
    
    ResultSet rs = st.executeQuery(req);
    
    while (rs.next()) {
        Livraison p = new Livraison();
        
        
            p.setNom_livraison(rs.getString(2));
            p.setDate(rs.getString(3));
            p.setDestination(rs.getString(4));
                    
        temp.add(p);
    }
    
    return temp;
}

    @Override
    public void ajouter(Livraison t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Livraison t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Livraison> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Livraison getOneById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }








    
    
    
    
}

