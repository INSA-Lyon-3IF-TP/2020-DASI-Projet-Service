package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 *
 * @author Nicolas Trouin et Fabien Narboux
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Medium implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    protected String denomination;
    protected Genre genre;
    protected String presentation;
    @OneToMany(mappedBy="medium")
    private List<Consultation> consultations;

    protected Medium() {}
    
    public Long getId() {
        return id;
    }
    
    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresantation(String presantation) {
        this.presentation = presantation;
    }
    
    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void addConsultation(Consultation consultation) {
        this.consultations.add(consultation);
    }
    
    public void removeConsultation(Consultation consultation) {
        this.consultations.remove(consultation);
    }
    
    @Override
    public String toString() {
        return "Medium{" + "id=" + id + ", denomination=" + denomination + ", genre=" + genre + ", presentation=" + presentation + '}';
    }
}
