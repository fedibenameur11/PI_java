/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;
import edu.esprit.services.UsersService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import edu.esprit.entities.users;
import edu.esprit.util.DataSource;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.RadioButton;
import edu.esprit.entities.login;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class LoginController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    Connection cnx = DataSource.getInstance().getCnx();
    @FXML
    private TextField username;
    @FXML
    private Button reset;
    @FXML
    private Button Logiiin;

    private final String path = "src\\edu\\esprit\\data\\LoginData.ini";
    @FXML
    private PasswordField password;
    @FXML
    private RadioButton remember;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsersService userService = new UsersService();
        try {
            userService.readinifile(path, username, password);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    


    

    @FXML
    private void login(ActionEvent event) throws IOException {
        login Log_in = login.getInstance();
        UsersService su = new UsersService();
        
        users u = su.getuserbyemailandpass(username.getText(), password.getText());
        if (username.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Email ne doit pas etre vide!", ButtonType.OK);
            a.showAndWait(); 
        } else if (password.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Mot de passe ne doit pas etre vide!", ButtonType.OK);
            a.showAndWait(); 
        }else{
        if (u.getPassword().equals(password.getText()) || u.getEmail().equals(username.getText()) ) {
        
        Log_in.setId(u.getId());
        Log_in.setNom(u.getNom());
        Log_in.setPrenom(u.getPrenom());
        Log_in.setUsername(u.getEmail());
        Log_in.setPassword(u.getPassword());
        Log_in.setAdresse(u.getAdresse());
        Log_in.setTelephone(u.getTelephone());
        Log_in.setCode_postale(u.getCode_postale());
        
            System.out.println(u.getId());
        System.out.println(Log_in.getId());
        System.out.println(Log_in.getPassword());
        System.out.println(Log_in.getUsername());
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Authentifié avec Succées!", ButtonType.OK);
            a.showAndWait(); 
            
            Parent root = FXMLLoader.load(getClass().getResource("UserInterface.fxml"));
            Scene homaepageScene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(homaepageScene);
            appStage.show(); 
            if (!remember.isSelected()) {
               su.Deleteinfo(path, path, path);
            } else if (remember.isSelected()) {
                su.createiniFile(path,username.getText(), password.getText());
            }
        

        } else{
        
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Les données sont invalides!", ButtonType.OK);
            a.showAndWait(); 
        }
    }

    }


    
    /***********************************************/
         private boolean Validateemail() {
        Pattern p = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher m = p.matcher(username.getText());
        if (((username.getText().length() > 8))&& (m.find() && m.group().equals(username.getText()))) {
            username.setEffect(null);
            return true;
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Email Invalide", ButtonType.OK);
            a.showAndWait();
            return false;
        }
    }
     
    

    private boolean Validatemotdepasse() {
        Pattern p = Pattern.compile("[a-zA-Z_0-9]+");
        Matcher m = p.matcher(username.getText());
                if (((username.getText().length() > 7))
                && (m.find() && m.group().equals(username.getText()))) {
            username.setEffect(null);
            return true;
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Mot de passe invalide", ButtonType.OK);
            a.showAndWait();
            return false;
        }
        
    }



    @FXML
    private void registrationpage(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void reset(ActionEvent event) {
        password.setText(null);
        username.setText(null);
    }
    


}

