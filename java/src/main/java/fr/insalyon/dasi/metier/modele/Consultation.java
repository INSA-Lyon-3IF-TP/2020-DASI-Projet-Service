/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Nicolas
 */
@Entity
public class Consultation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date heureDemande;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date heureDebut;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date heureFin;
    private String commentaire;
    
    @ManyToOne
    private Client client;
    
    @ManyToOne
    private Employe employe;
        
    @ManyToOne
    private Medium medium;

    protected Consultation() {
    }

    public Consultation(Date heureDemande) {
        this.heureDemande = heureDemande;
    }

    @Override
    public String toString() {
        return "Consultation{" + "id=" + id + ", heureDemande=" + heureDemande + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + ", commentaire=" + commentaire + ", client=" + client + ", employe=" + employe + ", medium=" + medium + '}';
    }
    
    
}
