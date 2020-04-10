/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import javax.persistence.EntityManager;

/**
 *
 * @author Nicolas Trouin et Fabien Narboux
 */
public class ConsultationDao {
    public void creer(Consultation consultation) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        Client client = consultation.getClient();
        client.addConsultation(consultation);
        em.merge(client);
        
        Employe employe = consultation.getEmploye();
        employe.addConsultation(consultation);
        em.merge(employe);
        
        Medium medium = consultation.getMedium();
        medium.addConsultation(consultation);
        em.merge(medium);
        
        em.persist(consultation);
    }
    
    public Consultation chercherParId(Long consultationId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Consultation.class, consultationId); // renvoie null si l'identifiant n'existe pas
    }
}
