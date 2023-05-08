/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

class MyListCell extends ListCell<MyData> {

    private AnchorPane myAnchorPane;
    private Label nomLabel;
    private Label prenomLabel;
    private Label emailLabel;
    private Label telephoneLabel;

    public MyListCell() {
        super();
        myAnchorPane = new AnchorPane();
        nomLabel = new Label();
        prenomLabel = new Label();
        emailLabel = new Label();
        telephoneLabel = new Label();

        myAnchorPane.getChildren().addAll(nomLabel,prenomLabel,emailLabel,telephoneLabel);

        AnchorPane.setLeftAnchor(nomLabel, 10.0);
        AnchorPane.setTopAnchor(nomLabel, 20.0);

        AnchorPane.setLeftAnchor(prenomLabel,110.0);
        AnchorPane.setTopAnchor(prenomLabel, 20.0);
        
        AnchorPane.setLeftAnchor(emailLabel, 210.0);
        AnchorPane.setTopAnchor(emailLabel, 20.0);

        AnchorPane.setLeftAnchor(telephoneLabel, 410.0);
        AnchorPane.setTopAnchor(telephoneLabel, 20.0);

    }

    @Override
    protected void updateItem(MyData item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
           nomLabel.setText(item.getNom());
           prenomLabel.setText(item.getPrenom());
           telephoneLabel.setText(item.getTelephone());
           emailLabel.setText(item.getEmail());

            setGraphic(myAnchorPane);
        }
    }
}
