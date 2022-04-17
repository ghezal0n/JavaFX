/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Produit;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Mehdi
 */
public class Afficher_pFXMLController implements Initializable {
   
    @FXML
    private TableView<Produit> tableview;  
    @FXML
    private TableColumn<Produit, Integer> idC;
    @FXML
    private TableColumn<Produit, String> nomC;
    @FXML
    private TableColumn<Produit, Integer> qteC;
    @FXML
    private TableColumn<Produit, Double> prixC;
    @FXML
    private TableColumn<Produit, String> descC;
    @FXML
    private TableColumn<Produit, String> catC;
    @FXML
    private TableColumn<Produit, String> bC;
    
    ObservableList list ;
    @FXML
    private Button ajouter;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            ProduitService ps = new ProduitService();
        List<Produit> produit = ps.recuperer();
        list = FXCollections.observableList(produit);
        tableview.setItems(list);
        
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
        qteC.setCellValueFactory(new PropertyValueFactory<>("qte"));
        prixC.setCellValueFactory(new PropertyValueFactory<>("prix"));
        descC.setCellValueFactory(new PropertyValueFactory<>("descrp"));
        catC.setCellValueFactory(new PropertyValueFactory<>("catego"));
        bC.setCellValueFactory(new PropertyValueFactory<>("button"));
    }    

    @FXML
    private void ajouter(ActionEvent event) {
          if(nomC.getText().isEmpty()||qteC.getText().isEmpty()||prixC.getText().isEmpty()||descC.getText().isEmpty()||catC.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "verifer les champs");  
        }
        else
        {
        String nom = nomC.getText();
        Integer qte = Integer.parseInt(qteC.getText());
        Double prix = Double.parseDouble(prixC.getText());
         String descrp = descC.getText();
         String catego = catC.getText();
        Reclamations c = new Reclamations(5,type,description,email);
        ReclamationsServices sc = new ReclamationsServices();
        sc.ajouterReclamations(c);
                    JOptionPane.showMessageDialog(null, "ajout avec succes");
        txttype.clear();
        txtdescprition.clear();
        txtemail.clear();
       
         ArrayList<Reclamations> publiciteList =  (ArrayList<Reclamations>) sc.getReclamations() ; 
    ObservableList<Reclamations> data = FXCollections.observableArrayList(publiciteList);
    
    tv.setItems(data);
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
         colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
         }
    }

    
}    
    
