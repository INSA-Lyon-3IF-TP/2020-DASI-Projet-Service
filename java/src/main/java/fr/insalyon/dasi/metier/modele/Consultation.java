/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    public Consultation(Date heureDemande, Client client, Employe employe, Medium medium) {
        this.heureDemande = heureDemande;
        this.client = client;
        this.employe = employe;
        this.medium = medium;
    }

    public void setHeureDemande(Date heureDemande) {
        this.heureDemande = heureDemande;
    }

    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Long getId() {
        return id;
    }

    public Date getHeureDemande() {
        return heureDemande;
    }

    public Date getHeureDebut() {
        return heureDebut;
    }

    public Date getHeureFin() {
        return heureFin;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Client getClient() {
        return client;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Medium getMedium() {
        return medium;
    }

    @Override
    public String toString() {
        return "Consultation{" + "id=" + id + ", heureDemande=" + heureDemande + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + ", commentaire=" + commentaire + ", client=" + client + ", employe=" + employe + ", medium=" + medium + '}';
    }
    
    
}
