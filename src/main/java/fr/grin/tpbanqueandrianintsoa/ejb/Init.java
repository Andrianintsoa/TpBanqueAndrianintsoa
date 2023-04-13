/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.grin.tpbanqueandrianintsoa.ejb;

import fr.grin.tpbanqueandrianintsoa.entities.CompteBancaire;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

/**
 *
 * @author tsior
 */
@Singleton
@Startup
public class Init {
    @EJB
    private GestionnaireCompte gestion;
    
    @PostConstruct
    public void init(){
        gestion.creerCompte(new CompteBancaire("John Lennon", 150000 ));
        gestion.creerCompte(new CompteBancaire("Paul McCartney", 950000 ));
        gestion.creerCompte(new CompteBancaire( "Ringo Starr", 20000  ));
        gestion.creerCompte(new CompteBancaire("Georges Harrisson", 100000));
    }
}
