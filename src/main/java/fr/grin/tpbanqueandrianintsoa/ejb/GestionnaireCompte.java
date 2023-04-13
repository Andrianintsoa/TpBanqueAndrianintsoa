/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.grin.tpbanqueandrianintsoa.ejb;

import fr.grin.tpbanqueandrianintsoa.entities.CompteBancaire;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author tsior
 */
@DataSourceDefinition (
    className="com.mysql.cj.jdbc.MysqlDataSource",
    name="java:app/jdbc/banque",
    serverName="localhost",
    portNumber=3306,
    user="root",              // nom et
    password="root1234", // mot de passe que vous avez donnés lors de la création de la base de données
    databaseName="banque",
    properties = {
      "useSSL=false",
      "allowPublicKeyRetrieval=true"
    }
)
@Stateless
public class GestionnaireCompte {
        @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    public void creerCompte(CompteBancaire c){
        em.persist(c);
    }    
    
    public List<CompteBancaire> getAllComptes() {
       TypedQuery query = em.createNamedQuery("CompteBancaire.findAll", CompteBancaire.class);
       return query.getResultList();
    }

    public CompteBancaire update(CompteBancaire comptebancaire) {
       return em.merge(comptebancaire);
    }

    public void persist(CompteBancaire comptebancaire) {
       em.persist(comptebancaire);
    }
    
    public Long nbComptes(){
        String req = "select count(c) from CompteBancaire c";
        TypedQuery<Long> query = em.createQuery(req , Long.class);
        return query.getSingleResult();
    }
    
    public CompteBancaire findById(long id) {  
        return em.find(CompteBancaire.class, id);  
    } 
    
    public void transferer(CompteBancaire source, CompteBancaire destination,
            int montant) {
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
    }
  
    
}
