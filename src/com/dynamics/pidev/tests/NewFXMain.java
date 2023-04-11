/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dynamics.pidev.tests;

import com.dynamics.pidev.entites.Salle;
import com.dynamics.pidev.entites.abonnementSalle;
import com.dynamics.pidev.services.AbonnementService;
import com.dynamics.pidev.services.SalleService;
import com.dynamics.pidev.utils.MyConnexion;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.awt.Toolkit;
/**
 *
 * @author MSI
 */
public class NewFXMain extends Application{

  @Override
    public void start(Stage primaryStage) throws Exception {
           
        try {
            
          Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherSalle1.fxml"));
            
           Scene scene = new Scene(root);
        
            primaryStage.setTitle("Sportera's");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        
        } catch (IOException ex) {
           System.err.println(ex.getMessage());
        }
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}

//public class MainClass {
    
  //  public static void main(String[] args) throws SQLException {
    //    MyConnexion cn1 = MyConnexion.getInstance();
        //MyConnexion cn2 = MyConnexion.getInstance();
        //MyConnexion cn3 = MyConnexion.getInstance();
        //MyConnexion cn4 = MyConnexion.getInstance();
        //MyConnexion cn5 = MyConnexion.getInstance();
        
      //  System.out.println(cn1.hashCode());
        //System.out.println(cn2.hashCode());
        //System.out.println(cn3.hashCode());
        //System.out.println(cn4.hashCode());
        //System.out.println(cn5.hashCode());
        //AbonnementService sp1 = new AbonnementService();
        //sp1.deleteOne(9);
        //SalleService sp = new SalleService();
        //sp.deleteOne(14);
        //System.out.println(sp.selectAll());
        
        //Salle p = new Salle(0, "Arena Gym", "Lac 2", 28564732, 1098, "Tunis", 400);
        
        
          //  sp.insertOne(p);
            
           // sp.updateOne(p,67);
            
        
        //System.out.println(sp1.selectAll());
        //abonnementSalle p1 = new abonnementSalle(0, "sept mois");
        
        
          //  sp1.insertOne1(p1);
            
            
            //sp1.updateOne(p1,80);
        
        
        
//}
    
//}
