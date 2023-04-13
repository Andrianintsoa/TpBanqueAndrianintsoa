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
import jakarta.faces.application.FacesMessage.Severity;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;

/**
 *
 * @author tsior
 */
@Named(value = "transfertBean")
@ViewScoped
public class TransfertBean implements Serializable {
    private int idSource;
    private int idDestination;
    private int montant;
    
    @EJB
    private GestionnaireCompte gestionCompte ;
    
    public int getIdSource() {
        return idSource;
    }

    public void setIdSource(int idSource) {
        this.idSource = idSource;
    }

    public int getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(int idDestination) {
        this.idDestination = idDestination;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
    
    
     public String submitTransfert(){
        if(idSource == idDestination){
            Util.messageErreur("Veuillez choisir un autre compte", "", "form:source");
           return null;
        }
        CompteBancaire compteSource = gestionCompte.findById(idSource);
        CompteBancaire compteDestination = gestionCompte.findById(idDestination); 
        Severity severity = FacesMessage.SEVERITY_ERROR;
        Boolean test = true;
        if(compteSource == null){
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
           test = false;
        }
        if(compteDestination == null){
           Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destination");
           test = false;
        }
        if(montant <= 0){
           Util.messageErreur("Le montant doit être supérieur à 0", "Le montant doit être supérieur à 0", "form:montant");
           test = false;
        }
        
        if(test){
            
            Severity severityUpdate = null;  
            String summaryUpdate = "";
            String messageUpdate = "";
            try{
                gestionCompte.transferer(compteSource, compteDestination, montant);
                severity = FacesMessage.SEVERITY_INFO;
                summaryUpdate = "Le transfert de " + compteSource.getNom() + " de " + montant + "€ a été enregistré";
            }catch(Exception e){
                severity = FacesMessage.SEVERITY_ERROR;
                summaryUpdate = "transfer failed";
                messageUpdate = e.getMessage();

            }finally{
                FacesMessage facesMessage = new FacesMessage(severity, summaryUpdate, messageUpdate);
                //FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                Util.addFlashMessage(facesMessage);
            }
            
            return "listeComptes?faces-redirect=true";
        }
        
        return "";
    }
    /**
     * Creates a new instance of TransfertBean
     */
    public TransfertBean() {
    }
    
}
