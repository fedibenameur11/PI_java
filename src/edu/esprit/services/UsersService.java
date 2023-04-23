/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.util.DataSource;
import com.jfoenix.controls.JFXRadioButton;
import java.sql.Connection;
import edu.esprit.entities.users;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.PasswordField;
import org.ini4j.Wini;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;

/**
 *
 * @author wassim
 */
public class UsersService {
            Connection cnx = DataSource.getInstance().getCnx();
        public String n, m;
        public String passwordF;
        
        //Fonction d'ajout d'un utilisateur
   public void ajouter(users u) throws SQLException {
    String req = "INSERT INTO `users`(  `nom`, `prenom`, `email`, `password`,`adresse`, `telephone`, `code_postale`)  VALUES (?,?,?,?,?,?,?)";
    PreparedStatement ps = cnx.prepareStatement(req);
    try {
        
       
        
        ps.setString(3, u.getNom());
        ps.setString(4, u.getPrenom());
        ps.setString(1, u.getEmail());
        ps.setString(2, u.getPassword());
        ps.setString(5, u.getAdresse());
        ps.setInt(6, u.getTelephone());
        ps.setInt(7, u.getCode_postale());

        ps.executeUpdate();
        System.out.println("Utilisateur created !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


public void modifier(int id, users u) {
    String sql = "UPDATE users SET  nom=?, prenom=?, email=?, password=?, adresse=?, telephone=?, code_postale=? WHERE id=" + id;
    try {
        PreparedStatement ste = cnx.prepareStatement(sql);
        
        ste.setString(3, u.getNom());
        ste.setString(4, u.getPrenom());
        ste.setString(1, u.getEmail());
        ste.setString(2, u.getPassword());
        ste.setString(5, u.getAdresse());
        ste.setInt(6, u.getTelephone());
        ste.setInt(7, u.getCode_postale());

        ste.executeUpdate();
        System.out.println("********************** MODIFIED ****************************************");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

        
                //Fonction d'affichage de tous les utilisateurs
    public List<users> getAll() {
    List<users> list = new ArrayList<>();
    try {
        String req = "Select * from users";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            users u = new users(
                rs.getInt("id"), 
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("adresse"),
                rs.getInt("telephone"),
                rs.getInt("code_postale")
            );
            list.add(u);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}


    

    
     //Fonction de supression d'un utilisateur
    public void delete(int id) {
        try {
            String req = "DELETE FROM `users` WHERE id  = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     /***********************************************Login Void *********************************************************************/
    
    public users getuserbyemailandpass(String email, String password) {
    users user = null;
    try {
        String req = "SELECT * FROM users WHERE email = ?";
        PreparedStatement psmt = cnx.prepareStatement(req);
        psmt.setString(1, email);
        ResultSet rs = psmt.executeQuery();
        if (rs.next()) {
            String hashedPassword = rs.getString("password");
            if (password.equals(hashedPassword)) {
                user = new users() {} ;
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setPassword(hashedPassword);
                user.setAdresse(rs.getString("adresse"));
                user.setTelephone(rs.getInt("telephone"));
                user.setCode_postale(rs.getInt("code_postale"));
              
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return user;
}
    
    /***********************************Create login files****************************************/
        public void createiniFile(String path, String user, String pass) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            Wini wini = new Wini(new File(path));
            wini.put("Login data", "Email", user);
            wini.put("Login data", "Password", pass);

            wini.store();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
            public void readinifile(String path, TextField userid, PasswordField passid, JFXRadioButton remember_me) {
        File file = new File(path);
        if (file.exists()) {
            try {
                Wini wini = new Wini(new File(path));
                String username = wini.get("Login data", "Email");
                String password = wini.get("Login data", "Password");
                if ((username != null && !username.equals("")) && (password != null && !password.equals(""))) {
                    userid.setText(username);
                    passid.setText(password);
                    remember_me.setSelected(true);
                }
            }  catch (IOException ex) {
                Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
            
                     
        public void readinifile(String path, TextField userid, PasswordField passid) throws IOException {
        File file = new File(path);
        if (file.exists()) {
                Wini wini = new Wini(new File(path));
                String username = wini.get("Login data", "Email");
                String password = wini.get("Login data", "Password");
                if ((username != null && !username.equals("")) && (password != null && !password.equals(""))) {
                    userid.setText(username);
                    passid.setText(password);
                }
        }
    }

        
            public void Deleteinfo(String path, String user, String pass) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            Wini wini = new Wini(new File(path));
            wini.remove("Login data", "Email");
            wini.remove("Login data", "Password");
            wini.store();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
        



}