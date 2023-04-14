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
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;

/**
 *
 * @author tsior
 */
@Named(value = "updateCompte")
@ViewScoped
public class UpdateCompte implements Serializable {

    private int id;
    private CompteBancaire compte;

    @EJB
    private GestionnaireCompte gestionCompte;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public GestionnaireCompte getGestionCompte() {
        return gestionCompte;
    }

    public void setGestionCompte(GestionnaireCompte gestionCompte) {
        this.gestionCompte = gestionCompte;
    }

    /**
     * Creates a new instance of UpdateCompte
     */
    public UpdateCompte() {
    }

    public void loadCompte() {
        compte = gestionCompte.findById(id);
    }

    // La méthode doit avoir cette signature.
    public void validateSolde(FacesContext fc, UIComponent composant, Object valeur) {
        int solde = (int) valeur;
        if (solde < 0) {
            FacesMessage message
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Le solde doit être un supérieur à 0",
                            "Le solde doit être un supérieur à 0");
            throw new ValidatorException(message);
        }
    }

    public String submitAction() {
        gestionCompte.update(compte);
        Util.addFlashInfoMessage("Modification enregistré sur compte de " + compte.getNom());
        return "listeComptes?faces-redirect=true";
    }

}
