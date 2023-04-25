/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import com.dynamics.pidev.entites.Salle;
import com.dynamics.pidev.gui.AfficherSalle1Controller;
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
 * @author MSI
 */
public class ExportPdf {
    
      @FXML
    public void exportPDF(ActionEvent event,Salle selectedSalle) throws SQLException, IOException{
        
         
    if (selectedSalle != null) {
        Document doc = new Document(PageSize.A4);
      try {   
          
          PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("C:/Users/MSI/Downloads/Salle.pdf"));
          doc.open();
          
          
          
          
         
            // Add logo image
            Image logo = Image.getInstance("C:/Users/MSI/Desktop/pidev java/src/com/dynamics/pidev/images/logoo.png");
            logo.setAlignment(Element.ALIGN_CENTER);
            logo.scaleToFit(150, 150);
            doc.add(logo);

            // Add title
            Paragraph title = new Paragraph("Salle de Sport "+selectedSalle.getNom_salle(), new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 18, com.itextpdf.text.Font.BOLD,new BaseColor(0, 0, 140)));
            title.setAlignment(Element.ALIGN_CENTER);
            doc.add(title);
            // Add gym name and address
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Font labelFont = new Font(Font.FontFamily.HELVETICA, 12);
            Font valueFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            Paragraph gymName = new Paragraph("Nom: ", labelFont);
            gymName.add(new Paragraph(selectedSalle.getNom_salle(), valueFont));
            gymName.setSpacingAfter(10f);
            doc.add(gymName);

            Paragraph gymAddress = new Paragraph("Adresse: ", labelFont);
            gymAddress.add(new Paragraph(selectedSalle.getAdresse_salle(), valueFont));
            gymAddress.setSpacingAfter(10f);
            doc.add(gymAddress);

            Paragraph gymPhone = new Paragraph("Tel: ", labelFont);
            gymPhone.add(new Paragraph(String.valueOf(selectedSalle.getNum_telephone()), valueFont));
            gymPhone.setSpacingAfter(10f);
            doc.add(gymPhone);
            
            Paragraph gymCodePostale = new Paragraph("Code Postale: ", labelFont);
            gymCodePostale.add(new Paragraph(String.valueOf(selectedSalle.getCodepostal()), valueFont));
            gymCodePostale.setSpacingAfter(10f);
            doc.add(gymCodePostale);
            
            Paragraph gymVille = new Paragraph("Ville: ", labelFont);
            gymVille.add(new Paragraph(selectedSalle.getVille(), valueFont));
            gymVille.setSpacingAfter(10f);
            doc.add(gymVille);

            Paragraph gymPrix = new Paragraph("Prix: ", labelFont);
            gymPrix.add(new Paragraph(String.valueOf(selectedSalle.getPrix_abonnement())+"dt / mois", valueFont));
            gymPrix.setSpacingAfter(10f);
            doc.add(gymPrix);
            

            doc.close();
          
          
          
           Desktop.getDesktop().open(new File ("C:/Users/MSI/Downloads/Salle.pdf"));
          
      } catch (FileNotFoundException ex) {
          Logger.getLogger(AfficherSalle1Controller.class.getName()).log(Level.SEVERE, null, ex);
      } catch (DocumentException ex) {
          Logger.getLogger(AfficherSalle1Controller.class.getName()).log(Level.SEVERE, null, ex);
      }

    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Pas de salle séléctionnée");
        alert.setContentText("S'il vous plait de séléctionner une salle");
        alert.showAndWait();
   }
       
    }
    
    
}
