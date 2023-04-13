/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package fr.grin.tpbanqueandrianintsoa.jsf;

import fr.grin.tpbanqueandrianintsoa.ejb.GestionnaireCompte;
import fr.grin.tpbanqueandrianintsoa.entities.CompteBancaire;
import fr.grin.tpbanqueandrianintsoa.jsf.util.Util;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;

/**
 *
 * @author tsior
 */
@Named(value = "ajoutCompte")
@ViewScoped
public class AjoutCompte implements Serializable {
    
    @NotNull(message = "Nom est obligatoire")
    private String nom;
    
    @PositiveOrZero
    private int solde;
    
    @EJB
    private GestionnaireCompte gestionCompte ;

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String submitAction() {
        
        if(nom.length() != 0 && solde >= 0){
            
            FacesMessage.Severity severityUpdate = null;  
            String summaryUpdate = "";
            String messageUpdate = "";
            FacesMessage.Severity severity = FacesMessage.SEVERITY_ERROR;
            String nextUrl= "listeComptes?faces-redirect=true";
            try{
                gestionCompte.creerCompte(new CompteBancaire(nom , solde));
                severity = FacesMessage.SEVERITY_INFO;
                summaryUpdate = "Le compte de " + this.nom + " a été enregistré";
            }catch(Exception e){
                severity = FacesMessage.SEVERITY_ERROR;
                summaryUpdate = "saving failed";
                messageUpdate = e.getMessage();
                nextUrl = "";

            }finally{
                FacesMessage facesMessage = new FacesMessage(severity, summaryUpdate, messageUpdate);
                Util.addFlashMessage(facesMessage);
                return nextUrl;
            }
                        
        }
            
        return null;
    }
    
    
}
