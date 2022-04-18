/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import entities.lignecommande;
import entities.commande;
import services.commandeService;
import services.lignecommandeService;
/**
 *
 * @author khouloud
 */
public class Main {

    public static void main(String[] args) {
        commandeService ps = new commandeService();
        lignecommandeService pr = new lignecommandeService();
       // ps.supprimer (2);
        //ps.modifier (1,"non livree");
        //ps.recuperer();
        //ps.ajouter (new commande (2,"2", 25-05-1999 ,"image","livree"));
  
        //pr.supprimerlc (4);
        //pr.modifierlc (3,60);
        //pr.recupererlc();
     // pr.ajouterlc (new lignecommande (2,"1","1",25));

        System.out.println(pr.recupererlc());
    }

}
//ps.supprimer (2);
//ps.modifier (4,"non livree");
