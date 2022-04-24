/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.commande;
import static gui.pdf.printNode;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.commandeService;

/**
 * FXML Controller class
 *
 * @author khouloud
 */
public class AfficherCFXMLController implements Initializable {

    @FXML
    private TableView<commande> table_commande;

    @FXML
    private TableColumn<commande, Integer > col_id;

    @FXML
    private TableColumn<commande, String> col_id_user_id;

    @FXML
    private TableColumn<commande, String > col_date_cmd;

    @FXML
    private TableColumn<commande, String> col_image;

    @FXML
    private TableColumn<commande, String> col_statut;
    @FXML
    private TextField recherche;
    
    ObservableList list ;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
      @FXML
    private Button pdf;
    commandeService ss = new commandeService();
    @FXML
    private AnchorPane aaa;
    private Button modifier;

   public static  commande c  ;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     ObservableList<commande> observableList = FXCollections.observableArrayList();
     
        commandeService ps = new commandeService();
        List<commande> commandes = ps.recuperer();
        list = FXCollections.observableList(commandes);
        table_commande.setItems(list);
        
        col_id_user_id.setCellValueFactory(new PropertyValueFactory<commande,String>("id_user_id"));
        col_date_cmd.setCellValueFactory(new PropertyValueFactory<commande,String>("date_cmd"));
        col_image.setCellValueFactory(new PropertyValueFactory<commande,String>("image"));
        col_statut.setCellValueFactory(new PropertyValueFactory<commande,String>("statut"));
        

        FilteredList<commande> filteredData = new FilteredList<>(list, b -> true);

        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(commande -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(commande.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (commande.getStatut().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }else if (String.valueOf(commande.getImage()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(commande.getId_user_id()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } 
                 else {
                    return false; // Does not match.
                }
            });
        });


    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
    Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/AjoutCFXML.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    

    
}
   
    @FXML
    private void supprimer(ActionEvent event) {
         commande selectedItem = table_commande.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Etes vous sure de supprimer cette element ?");   
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        commandeService ps = new commandeService() ; 
        table_commande.getItems().remove(selectedItem);
        ps.supprimer(selectedItem.getId());
        }
        
        }
        else {
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un element à supprimer.");

        alert.showAndWait();
        }
    }

  // @FXML
  // private void modifier(ActionEvent event) {
       //  try {
         // Parent root = FXMLLoader.load(getClass().getResource("ModifCCFXML.fxml"));
          // modifier.getScene().setRoot(root);
      // } catch (IOException ex) {
          //System.out.println(ex.getMessage());
      // }
    
//}
@FXML
    private void modifier(ActionEvent event) {

        try {
            System.out.println("You clicked me!");
            commande selectedItem = table_commande.getSelectionModel().getSelectedItem();
             c = ss.GetById(selectedItem.getId());
            System.out.println("selected by id validee!"
                    + selectedItem.getId() + " lobjet est" + c);

            Parent modifier = FXMLLoader.load(getClass().getResource("ModifCCFXML.fxml"));
            Scene modifier_scene = new Scene(modifier);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(modifier_scene);
            app_stage.show();
        } catch (IOException ex) {
            System.out.println("gui.AfficherCFXMLController.modifier()" + ex.getMessage());
        }

    }


      @FXML
    private void pdf(ActionEvent event) {
 try {
            printNode(aaa);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(AfficherCFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AfficherCFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AfficherCFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(AfficherCFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}