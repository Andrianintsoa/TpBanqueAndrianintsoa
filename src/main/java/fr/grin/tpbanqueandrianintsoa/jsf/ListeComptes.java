/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package fr.grin.tpbanqueandrianintsoa.jsf;

import fr.grin.tpbanqueandrianintsoa.ejb.GestionnaireCompte;
import fr.grin.tpbanqueandrianintsoa.entities.CompteBancaire;
import fr.grin.tpbanqueandrianintsoa.jsf.util.Util;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author tsior
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {
    private List<CompteBancaire> BanqueList;  
    @EJB
    private GestionnaireCompte gestionnaireCompte; 
    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }
    public List<CompteBancaire> getAllComptes() {
    if (BanqueList == null) {
      BanqueList = gestionnaireCompte.getAllComptes();
    }
    return BanqueList;
  }  
    public String supprimerCompte(CompteBancaire compteBancaire) {
        gestionnaireCompte.supprimerCompte(compteBancaire);
        Util.addFlashInfoMessage("Compte de " + compteBancaire.getNom() + " supprimé");
        return "listeComptes?faces-redirect=true";
    }
}
