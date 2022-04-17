/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Util.MyDB;

/**
 *
 * @author Mehdi
 */
public class ProduitService implements IProduit<Produit>{
Connection connection; 

    public ProduitService() {
        connection=Util.MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Produit pr) {
    try {
      /*  String req = "insert into personne(nom,prenom,age) "
                + "values('"+t.getNom()+"','"+t.getPrenom()+"',"+t.getAge()+")";
        Statement st = connection.createStatement();
       st.executeUpdate(req);*/
        String req1="insert into Produit (nom,qte,prix,descrp,catego) values(?,?,?,?,?)";
        PreparedStatement ps=connection.prepareStatement(req1);
        ps.setString(1, pr.getNom());
        ps.setInt(2, pr.getQte());
        ps.setDouble(3, pr.getPrix());
        ps.setString(4, pr.getDescrp());
        ps.setString(5, pr.getCatego());
        
        ps.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    }

    @Override
    public void modifier(int id, String nom) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //String req1="update Produit set (nom,qte,prix,descrp,catego) where id=id";
     try {
            String req = "UPDATE Produit SET nom = ? WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(req); 
            pst.setString(1, nom);
            pst.setInt(2, id);
            pst.executeUpdate();
            System.err.println("MISE A JOUR FAITE AVEC SUCCEE !! ");
        } catch (SQLException ex) {
            System.err.println("UN ERREUR S'EST PRODUIT LORS DE LA MODIFICATION !! ");
            System.err.println(ex.getMessage());
        }
    }
    

    @Override
    public void supprimer(int id) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
      try {
            String requete = "DELETE FROM Produit WHERE id = ? ";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, id);
            int rsltUpdate = pst.executeUpdate();
            if (rsltUpdate == 0) {
                System.err.println("EXITE AUCUN ELEMENT CORRESPOND AU DONNEE SAISIE !! \n AUCUNE SUPPRESSION N'EST EFFECTUEE !!");
            } else {
                System.out.println("EVENT DELETED SUCCESFULLY !!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      
    }

    @Override
    public List<Produit> recuperer() {
        List<Produit> list = new ArrayList<>();
        try{
            String req = "select * from produit";
            Statement st = connection.createStatement();
    
        ResultSet rs=st.executeQuery(req);
        while(rs.next())
        {
            Produit pr = new Produit();
            pr.setId(rs.getInt("id"));
            pr.setNom(rs.getString("nom"));
            pr.setQte(rs.getInt("qte"));
            pr.setPrix(rs.getDouble("prix"));
            pr.setDescrp(rs.getString("descrp"));
            pr.setCatego(rs.getString("catego"));
            list.add(pr);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        return list;
    }
    
}
