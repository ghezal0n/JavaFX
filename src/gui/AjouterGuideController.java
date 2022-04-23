/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Guide;
import Services.GuideService;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterGuideController implements Initializable {

    @FXML
    private Label titreLabel;
    @FXML
    private TextField titleTF;
    @FXML
    private TextField DescrpTF;
    @FXML
    private TextField nbrhTF;
    @FXML
    private TextField gameTF;
    @FXML
    private TextField CoachLab;
    @FXML
    private TextField PrixLab;
    @FXML
    private Label DATELabel;
    @FXML
    private Button ajouterBTn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void AjoutGuide(ActionEvent event) {
        String titreGuide = titleTF.getText();
        String Descrp = DescrpTF.getText();
        int nbrH = Integer.parseInt(nbrhTF.getText());
        String CoachName = CoachLab.getText();
        String GameName = gameTF.getText();
        int Prix = Integer.parseInt(PrixLab.getText());
        String dateguide = java.time.LocalDate.now().toString();
        DATELabel.setText(dateguide);
        System.out.println("datelabel" + DATELabel);

        Guide A = new Guide(titreGuide, Descrp, nbrH, dateguide, Prix, GameName, CoachName);
        GuideService B = new GuideService();

        if (titreGuide.isEmpty() || Descrp.isEmpty() || dateguide.isEmpty() || GameName.isEmpty() || CoachName.isEmpty() || nbrhTF.getText().isEmpty() || PrixLab.getText().isEmpty()) {   //controles de saisie non vide 

            JOptionPane.showMessageDialog(null, "Vous avez oublié de remplir vos champs");

        } 
        else if(titreGuide.isEmpty() && Descrp.isEmpty() && dateguide.isEmpty() && GameName.isEmpty() && CoachName.isEmpty() && nbrhTF.getText().isEmpty() &&PrixLab.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Vous devez Remplir tout les champs");
        }
        
        else {

            boolean existe = true;
            int i = 0;
            while (i < B.afficherGuide().size()) {
                if (B.afficherGuide().get(i).getTitre() == null ? titreGuide == null : B.afficherGuide().get(i).getTitre().equals(titreGuide)) {
                    JOptionPane.showMessageDialog(null, "Ce titre deja existe");
                    existe = true;
                    break;
                } else {
                    existe = false;
                }
                i++;
            }
            if (!existe) {
                try {
                    B.ajouterGuide(A);

                    System.out.println("bien ajouter");
                    // SendGrid Mail api 
                    Email from = new Email("bhjrou5@gmail.com");
                    String subject = "Clutch.gg ";
                    Email to = new Email("rawaa.blh@gmail.com");
                    Content content = new Content("text/plain", "Hello Dear Player we added a new Guide take a look you may find something you like");
                    Mail mail = new Mail(from, subject, to, content);

                    SendGrid sg = new SendGrid("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"
                    );
                    Request request = new Request();
                    try {
                        request.setMethod(Method.POST);
                        request.setEndpoint("mail/send");
                        request.setBody(mail.build());
                        Response response = sg.api(request);
                        System.out.println(response.getStatusCode());
                        System.out.println(response.getBody());
                        System.out.println(response.getHeaders());
                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
                    Parent addGuide = FXMLLoader.load(getClass().getResource("GestionGuide.fxml"));
                    Scene addguidescene = new Scene(addGuide);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(addguidescene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AjouterGuideController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
