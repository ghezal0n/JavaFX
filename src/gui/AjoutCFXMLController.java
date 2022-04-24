/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.commande;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import services.commandeService;

/**
 * FXML Controller class
 *
 * @author khouloud
 */
public class AjoutCFXMLController implements Initializable {

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_id_user_id;

    @FXML
    private DatePicker txt_date_cmd;

    @FXML
    private TextField txt_image;

    @FXML
    private TextField txt_statut;
   
    
      @FXML
    private Button ajouter;  

    @FXML
    private Button afficher;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  
    @FXML
    private void ajouter(ActionEvent event) {
        if(txt_id.getText().isEmpty()||txt_id_user_id.getText().isEmpty()||txt_statut.getText().isEmpty()||txt_image.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "verifer les champs");  
        }
    commande c = new commande();
        c.setId(Integer.parseInt(txt_id.getText()));
        c.setId_user_id(txt_id_user_id.getText());
        c.setDate_cmd(String.valueOf(txt_date_cmd.getValue()));
        c.setImage(txt_image.getText());
        c.setStatut(txt_statut.getText());
        
        commandeService ps = new commandeService();
        ps.ajouter(c);
        
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("commande ajoutée");
        a.show();
        
        String title = "succes ";
        String message = "Votre alert est bien reçu";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndWait();             
        txt_id.clear();
        txt_id_user_id.clear();
        txt_statut.clear();
        txt_image.clear();
        
    }
    @FXML
    private void afficher(ActionEvent event) {
     try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherCFXML.fxml"));
            txt_statut.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
   



   

 

}
 


