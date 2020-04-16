package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Nicolas Trouin et Fabien Narboux
 */
@Entity
public class Spirite extends Medium implements Serializable{
    
    private String support;

    protected Spirite() {
    }

    public Spirite(String denomination, Genre genre, String presentation, String support) {
        this.denomination = denomination;
        this.genre = genre;
        this.presentation = presentation;
        this.support = support;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    @Override
    public String toString() {
        return "Spirite{" + super.toString() + ", support=" + support + '}';
    }
}
