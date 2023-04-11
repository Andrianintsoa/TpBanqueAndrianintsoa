/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.grin.tpbanqueandrianintsoa.ejb;

import fr.grin.tpbanqueandrianintsoa.entities.CompteBancaire;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
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
       Query query = em.createNamedQuery("Banque.findAll");
       return query.getResultList();
    }

    public CompteBancaire update(CompteBancaire comptebancaire) {
       return em.merge(comptebancaire);
    }

    public void persist(CompteBancaire comptebancaire) {
       em.persist(comptebancaire);
    }
}
