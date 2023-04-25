/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import edu.esprit.entities.users;
import edu.esprit.services.UsersService;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import edu.esprit.entities.login;
import java.sql.SQLException;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.scene.control.TextField;

public class UserInterfaceController implements Initializable {

    @FXML
    private Button addUserButton;

    @FXML
    private Button editUserButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private Button refreshButton;

    @FXML
    private TableView<users> usersTable;

    @FXML
    private TableColumn<users, Integer> idColumn;

    @FXML
    private TableColumn<users, String> emailColumn;

    @FXML
    private TableColumn<users, String> passwordColumn;

    @FXML
    private TableColumn<users, String> nomColumn;

    @FXML
    private TableColumn<users, String> prenomColumn;

    @FXML
    private TableColumn<users, String> adresseColumn;

    @FXML
    private TableColumn<users, Integer> telephoneColumn;

    @FXML
    private TableColumn<users, Integer> codePostaleColumn;

    private UsersService usersService;
    @FXML
    private Button excel;
    @FXML
    private Button logout;
    @FXML
    private Label username;
    @FXML
    private TextField searchField;
    
    private ObservableList<users> usersList=  FXCollections.observableArrayList();

    private login Log_in = login.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usersService = new UsersService();
        username.setText(Log_in.getNom());
    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
    nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
    prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
    telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
    codePostaleColumn.setCellValueFactory(new PropertyValueFactory<>("code_postale"));

        loadUsers();

        addUserButton.setOnAction(e -> {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddUser.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add User");
        stage.setScene(new Scene(parent));
        stage.showAndWait();
        loadUsers();
    } catch (IOException ex) {
        System.out.println("Error opening Add User dialog: " + ex.getMessage());
    }
});


  editUserButton.setOnAction(e -> {
    users selectedUser = usersTable.getSelectionModel().getSelectedItem();
    if (selectedUser != null) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifyUser.fxml"));
            Parent parent = fxmlLoader.load();

            ModifyUserController modifyUserController = fxmlLoader.getController();
            modifyUserController.setCurrentUser(selectedUser);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Modify User");
            stage.setScene(new Scene(parent));
            stage.showAndWait();
            loadUsers();
        } catch (IOException ex) {
            System.out.println("Error opening Modify User dialog: " + ex.getMessage());
        }
    } else {
        System.out.println("No user selected for editing.");
    }
});






        deleteUserButton.setOnAction(e -> {
    // Delete the selected user
    users selectedUser = usersTable.getSelectionModel().getSelectedItem();
    if (selectedUser != null) {
        usersService.delete(selectedUser.getId());
        loadUsers();
    }
});




        refreshButton.setOnAction(e -> loadUsers());
    }
    

    private void loadUsers() {
        try {
            ObservableList<users> userList = FXCollections.observableArrayList(usersService.getAll());
            usersTable.setItems(userList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void Logout(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene homaepageScene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(homaepageScene);
            appStage.show();
        } catch (IOException ex) {
            Logger.getLogger(UserInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void excelfile(ActionEvent event) {
        ArrayList<users> data = new ArrayList<users>();
     
       
       try{  



    //creating an instance of HSSFWorkbook class  
//declare file name to be create   
    String filename = "C:\\Users\\wassim\\OneDrive\\Documents\\NetBeansProjects\\PI_dev\\src\\edu\\esprit\\DonnéeUtilisateurs.XLS";  
//creating an instance of HSSFWorkbook class  
    HSSFWorkbook workbook = new HSSFWorkbook();  
//invoking creatSheet() method and passing the name of the sheet to be created   
    HSSFSheet sheet = workbook.createSheet("User Details");   
//creating the 0th row using the createRow() method  
    HSSFRow rowhead = sheet.createRow((short)0);  
//creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method  
    rowhead.createCell(0).setCellValue("Nom");  
    rowhead.createCell(1).setCellValue("Prenom");  
    rowhead.createCell(2).setCellValue("Email");  
    rowhead.createCell(3).setCellValue("Mot de passe");  
    rowhead.createCell(4).setCellValue("Numéro de téléphone");  

    ObservableList<users> userlist = FXCollections.observableArrayList(usersTable.getItems());
             
                int rownum = 1;
                for (users USER : userlist) {
                HSSFRow row = sheet.createRow(rownum++);  
                HSSFRow headerRow = sheet.createRow(0);
                row.createCell(0).setCellValue(USER.getNom());
                row.createCell(1).setCellValue(USER.getPrenom());
                row.createCell(2).setCellValue(USER.getEmail());
                row.createCell(3).setCellValue(USER.getAdresse());
                row.createCell(4).setCellValue(USER.getCode_postale());
                row.createCell(4).setCellValue(USER.getTelephone());
                }
            
                FileOutputStream fileOut = new FileOutputStream(filename);  
                workbook.write(fileOut);  
            
                //closing the Stream  
                fileOut.close();  
                //closing the workbook  
                workbook.close();  
                //prints the message on the console  
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Excel File Has Been Generated Successfully", ButtonType.OK);
                a.showAndWait();
                }   
                catch (Exception e)   
                {  
                e.printStackTrace();  
                }                                 
        
    }
    
    @FXML
    private void reherche() throws SQLException{
    UsersService service = new UsersService();
    ObservableList<users> usersList = FXCollections.observableArrayList();
    usersList.addAll(service.getAll());
        
        FilteredList<users> filteredData = new FilteredList<>(usersList, b -> true);
      
	searchField.textProperty().addListener((observable, oldValue, newValue) -> {
		filteredData.setPredicate(g -> {
				if (newValue == null || newValue.isEmpty()) {
				return true;
			}
			String lowerCaseFilter = newValue.toLowerCase();

				if (g.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
                                        
				} 
                                else if (g.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				        return true;
                                }else if (g.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                  return true;
                                
                                
                                        
                                }else if (g.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				        return true;
                                        
                                } else if (Integer.toString(g.getTelephone()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                  return true;
                                } else if (Integer.toString(g.getCode_postale()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                  return true;
                                
				}
                                 
				else
					return false;
			
                        });
		});
		    SortedList<users> sortedData = new SortedList<>(filteredData);
		    sortedData.comparatorProperty().bind(usersTable.comparatorProperty());
		usersTable.setItems(sortedData);
    
        
  }


    
                           
        
    
}
