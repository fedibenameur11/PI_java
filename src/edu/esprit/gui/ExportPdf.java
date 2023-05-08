/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.gui;

import edu.esprit.entities.AbonnementCoach;
import edu.esprit.gui.CRUDAbonnementCoachFXMLController;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;


/**
 *
 * @author Alae
 */
public class ExportPdf {
    
    @FXML
    public void exportPDF(ActionEvent event,AbonnementCoach selectedAbonn) throws SQLException, IOException{
        
         
    if (selectedAbonn != null) {
        Document doc = new Document(PageSize.A4);
      try {   
          
          PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\wassim\\OneDrive\\Documents\\Abonnement.pdf"));
          doc.open();
            // Add logo image
            Image logo = Image.getInstance("C:\\Users\\wassim\\OneDrive\\Documents\\NetBeansProjects\\PI_dev\\src\\edu\\esprit\\gui\\logo_true.png");
            logo.setAlignment(Element.ALIGN_CENTER);
            logo.scaleToFit(150, 150);
            doc.add(logo);

            // Add title
            Paragraph title = new Paragraph("Abonnement avec un identifiant "+selectedAbonn.getId(), new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 18, com.itextpdf.text.Font.BOLD,new BaseColor(0, 0, 140)));
            title.setAlignment(Element.ALIGN_CENTER);
            doc.add(title);
            // Add gym name and address
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Font labelFont = new Font(Font.FontFamily.HELVETICA, 12);
            Font valueFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            Paragraph coach = new Paragraph("Coach: ", labelFont);
            coach.add(new Paragraph(selectedAbonn.getCoach_id().getNom(), valueFont));
            coach.setSpacingAfter(10f);
            doc.add(coach);

            Paragraph client = new Paragraph("Client: ", labelFont);
            client.add(new Paragraph(selectedAbonn.getClient_id().getNom(), valueFont));
            client.setSpacingAfter(10f);
            doc.add(client);

            Paragraph datedebut = new Paragraph("Date debut : ", labelFont);
            datedebut.add(new Paragraph(String.valueOf(selectedAbonn.getDateDebut()), valueFont));
            datedebut.setSpacingAfter(10f);
            doc.add(datedebut);
            
            Paragraph datefin = new Paragraph("Date Fin : ", labelFont);
            datefin.add(new Paragraph(String.valueOf(selectedAbonn.getDateFin()), valueFont));
            datefin.setSpacingAfter(10f);
            doc.add(datefin);
            
            Paragraph duree = new Paragraph("Duree de l'abonnement : ", labelFont);
            duree.add(new Paragraph(String.valueOf(selectedAbonn.getDuree())+" Mois", valueFont));
            duree.setSpacingAfter(10f);
            doc.add(duree);

            Paragraph statut = new Paragraph("Statut : ", labelFont);
            if (selectedAbonn.getStatut() == 0){
                statut.add(new Paragraph("En attente", valueFont));
            }
            if (selectedAbonn.getStatut() == 1){
                statut.add(new Paragraph("Acceptée", valueFont));
            }
            statut.setSpacingAfter(10f);
            doc.add(statut);
            

            doc.close();
          
          
          
           Desktop.getDesktop().open(new File ("C:\\Users\\wassim\\OneDrive\\Documents\\Abonnement.pdf"));
          
      } catch (FileNotFoundException ex) {
          Logger.getLogger(CRUDAbonnementCoachFXMLController.class.getName()).log(Level.SEVERE, null, ex);
      } catch (DocumentException ex) {
          Logger.getLogger(CRUDAbonnementCoachFXMLController.class.getName()).log(Level.SEVERE, null, ex);
      }

    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Pas d'abonnement séléctionnée");
        alert.setContentText("S'il vous plait de séléctionner un abonnement");
        alert.showAndWait();
   }
       
    }
}
