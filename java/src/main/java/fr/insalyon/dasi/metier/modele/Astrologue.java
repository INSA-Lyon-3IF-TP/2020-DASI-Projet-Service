package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Nicolas Trouin et Fabien Narboux
 */
@Entity
public class Astrologue extends Medium implements Serializable{
    
    private String formation;
    private Integer promo;

    protected Astrologue() {}

    public Astrologue(String denomination, Genre genre, String presentation,String formation, Integer promo) {
        this.denomination = denomination;
        this.genre = genre;
        this.presentation = presentation;
        this.formation = formation;
        this.promo = promo;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public Integer getPromo() {
        return promo;
    }

    public void setPromo(Integer promo) {
        this.promo = promo;
    }

    @Override
    public String toString() {
        return "Astrologue{"+ super.toString() + ", formation=" + formation + ", promo=" + promo + '}';
    }
    
}
