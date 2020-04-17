package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 *
 * @author Nicolas Trouin et Fabien Narboux
 */
@Entity
public class Employe implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private Genre genre;    
    private String telephone;
    private String adresse;
    @Column(unique = true)
    private String mail;
    private String motDePasse;
    private Boolean estOccupe = false;
    @OneToMany(mappedBy = "employe")
    private List<Consultation> consultations;
    @Version
    private Long version;
    
    protected Employe() {
    }

    public Employe(String nom, String prenom, Genre genre, String telephone, String adresse, String mail, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
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

    public Enum getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
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

    public Boolean getEstOccupe() {
        return estOccupe;
    }

    public void setEstOccupe(Boolean estOccupe) {
        this.estOccupe = estOccupe;
    }
    
    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void addConsultation(Consultation consultation) {
        this.consultations.add(consultation);
    }

    @Override
    public String toString() {
        return "Employe{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", genre=" + genre + ", telephone=" + telephone + ", adresse=" + adresse + ", mail=" + mail + ", motDePasse=" + motDePasse + ", estOccupe=" + estOccupe + '}';
    }
}
