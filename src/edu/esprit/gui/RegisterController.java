/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;


import edu.esprit.entities.users;
import edu.esprit.services.UsersService;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author wassi
 */
public class RegisterController implements Initializable {

     @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField adresseField;
    @FXML
    private TextField telephoneField;
    @FXML
    private TextField codePostaleField;
    @FXML
    

    private UsersService usersService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
     private void Registration(ActionEvent event) throws IOException, SQLException{
       if (emailField.getText().isEmpty() || passwordField.getText().isEmpty()|| nomField.getText().isEmpty() ||  prenomField.getText().isEmpty() || adresseField.getText().isEmpty() || telephoneField.getText().isEmpty() || codePostaleField.getText().isEmpty() ) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Aucun champ vide n'est accepté", ButtonType.OK);
            a.showAndWait();
        } else if (!Validateemail()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Email  invalide!", ButtonType.OK);
            a.showAndWait();
        }else if (!Validatenumtel()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Numéro de telephone  invalide!", ButtonType.OK);
            a.showAndWait();
        }
        else if (!Validatemotdepasse()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Mot de passe  invalide!", ButtonType.OK);
            a.showAndWait();   
        }
        else {
             UsersService su = new  UsersService();
            users u = new users(emailField.getText(),passwordField.getText(),nomField.getText(),prenomField.getText(),adresseField.getText(), Integer.parseInt(telephoneField.getText()), Integer.parseInt(codePostaleField.getText()) ) {} ;
            su.ajouter(u);
            List<users> all = su.getAll();
            emailField.clear();
            passwordField.clear();
            nomField.clear();
            prenomField.clear();
            adresseField.clear();
            telephoneField.clear();
            codePostaleField.clear();

            Alert a = new Alert(Alert.AlertType.INFORMATION, "You have been registred succesfully !", ButtonType.OK);
            a.showAndWait();
            
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Scene LoginpageScene = new Scene(root);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 appStage.setScene(LoginpageScene);
                appStage.show();
        }
        
    } 
    /*********************************************email API***************************************************************************/    

  
   
    /*********************************************CONTROLE DE SAISIE***************************************************************************/    
private boolean Validateemail() {
    Pattern p = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    Matcher m = p.matcher(emailField.getText());
    if (m.find() && m.group().equals(emailField.getText())) {
        emailField.setEffect(null);
        return true;
    } else {
        Alert a = new Alert(Alert.AlertType.ERROR, "Email Invalide", ButtonType.OK);
        a.showAndWait();
        return false;
    }
}

     
     

    private boolean Validatenumtel() {
        
        Pattern p = Pattern.compile("^\\d{8}$");
        Matcher m = p.matcher(telephoneField.getText());

        if ((telephoneField.getText().length() == 8)
                || (m.find() && m.group().equals(telephoneField.getText()))) {
           telephoneField.setEffect(null);
            return true;
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Numéro de telephone invalide", ButtonType.OK);
            a.showAndWait();
            return false;
        }
    }

    private boolean Validatemotdepasse() {
        Pattern p = Pattern.compile("[a-zA-Z_0-9]+");
        Matcher m = p.matcher(passwordField.getText());
                if (((passwordField.getText().length() > 7))
                && (m.find() && m.group().equals(passwordField.getText()))) {
            passwordField.setEffect(null);
            return true;
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Mot de passe invalide", ButtonType.OK);
            a.showAndWait();
            return false;
        }
        
    }



    
}
