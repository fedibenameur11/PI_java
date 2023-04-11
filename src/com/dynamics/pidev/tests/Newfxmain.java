/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dynamics.pidev.tests;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author trabe
 */
public class Newfxmain extends Application {

       @Override
    public void start(Stage primaryStage) {
       
        try {
            
           
            Parent root = FXMLLoader.load(getClass().getResource("../gui/AddQuestion.fxml"));
            
            Scene scene = new Scene(root);
        
            primaryStage.setTitle("Forums Question");
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
