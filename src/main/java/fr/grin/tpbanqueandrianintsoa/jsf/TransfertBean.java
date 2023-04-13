/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package fr.grin.tpbanqueandrianintsoa.jsf;

import fr.grin.tpbanqueandrianintsoa.ejb.GestionnaireCompte;
import fr.grin.tpbanqueandrianintsoa.entities.CompteBancaire;
import jakarta.ejb.EJB;
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
        CompteBancaire compteSource = gestionCompte.findById(idSource);
        CompteBancaire compteDestination = gestionCompte.findById(idDestination);
        gestionCompte.transferer(compteSource, compteDestination, montant);
        return "listeComptes?faces-redirect=true";
        
        
    }
    /**
     * Creates a new instance of TransfertBean
     */
    public TransfertBean() {
    }
    
}
