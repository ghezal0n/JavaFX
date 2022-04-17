/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Mehdi
 */
public class AjouterFXMLController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField qte;
    @FXML
    private TextField prix;
    @FXML
    private TextField descrp;
    @FXML
    private ChoiceBox<String> catego;
    private String[] choix = {"T-Shirt","Accessoire","Pull"};
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        catego.getItems().addAll(choix);
    }    

    @FXML
    private void ajouter(MouseEvent event) {
        Produit p = new Produit();
        p.setNom(nom.getText());
        p.setQte(Integer.parseInt(qte.getText()));
        p.setPrix(Double.parseDouble(prix.getText()));
        p.setDescrp(descrp.getText());
        //p.setCatego(catego.ChoiceBox<?>); 
        ProduitService ps = new ProduitService();
        ps.ajouter(p);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Produit ajouté");
        a.show();
    }

    @FXML
    private void afficher(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherFXML.fxml"));
            //age.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
