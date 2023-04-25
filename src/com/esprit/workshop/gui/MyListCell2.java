/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.workshop.gui;

import com.esprit.workshop.entites.User;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

class MyListCell2 extends ListCell<MyData2> {

    private AnchorPane myAnchorPane;
    private Label nomLabel;
    private Label prenomLabel;
    private Label emailLabel;
    private Label telephoneLabel;
    private Label dureeLabel;

    public MyListCell2() {
        super();
        myAnchorPane = new AnchorPane();
        nomLabel = new Label();
        prenomLabel = new Label();
        emailLabel = new Label();
        telephoneLabel = new Label();
        dureeLabel = new Label();

        myAnchorPane.getChildren().addAll(nomLabel,prenomLabel,emailLabel,telephoneLabel,dureeLabel);

      AnchorPane.setLeftAnchor(nomLabel, 10.0);
        AnchorPane.setTopAnchor(nomLabel, 20.0);

        AnchorPane.setLeftAnchor(prenomLabel,110.0);
        AnchorPane.setTopAnchor(prenomLabel, 20.0);
        
        AnchorPane.setLeftAnchor(emailLabel, 210.0);
        AnchorPane.setTopAnchor(emailLabel, 20.0);

        AnchorPane.setLeftAnchor(telephoneLabel, 410.0);
        AnchorPane.setTopAnchor(telephoneLabel, 20.0);
        
          AnchorPane.setLeftAnchor(dureeLabel, 510.0);
        AnchorPane.setTopAnchor(dureeLabel, 20.0);

    }

    @Override
    protected void updateItem(MyData2 item, boolean empty) {
        
       
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
          User u = item.getClient(); 
           nomLabel.setText(u.getNom());
           prenomLabel.setText(u.getPrenom());
           telephoneLabel.setText(Integer.toString(u.getTelephone()));
           emailLabel.setText(u.getEmail());
           dureeLabel.setText(Integer.toString(item.getDuree())+"mois" );
            setGraphic(myAnchorPane);
        }
    }
}
