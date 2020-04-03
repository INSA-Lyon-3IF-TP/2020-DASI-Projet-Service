package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author DASI Team
 */
@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String civilite;    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDeNaissance;
    private String telephone;
    private String adresse;
    @Column(unique = true)
    private String mail;
    private String motDePasse;
    
    @OneToOne(cascade=CascadeType.ALL)
    private ProfilAstral profilAstral;

    protected Client() {
    }

    public Client(String nom, String prenom, String civilite, Date dateDeNaissance, String telephone, String adresse, String mail, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.civilite = civilite;
        this.dateDeNaissance = dateDeNaissance;
        this.telephone = telephone;
        this.adresse = adresse;
        this.mail = mail;
        this.motDePasse = motDePasse;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public ProfilAstral getProfilAstral() {
        return profilAstral;
    }

    public void setProfilAstral(ProfilAstral profilAstral) {
        this.profilAstral = profilAstral;
    }
  
    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "Client : id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", civilite=" + civilite + ", dateDeNaissance=" + simpleDateFormat.format(dateDeNaissance) + ", telephone=" + telephone + ", adresse=" + adresse + ", mail=" + mail + ", motDePasse=" + motDePasse + ", profilAstral=" + profilAstral;
    }
    

}
