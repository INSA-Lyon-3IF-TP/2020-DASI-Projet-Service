package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Nicolas Trouin et Fabien Narboux
 */
@Entity
public class Cartomancien extends Medium implements Serializable {

    protected Cartomancien() {}

    public Cartomancien(String denomination, Genre genre, String presentation) {
        this.denomination = denomination;
        this.genre = genre;
        this.presentation = presentation;
    }

    @Override
    public String toString() {
        return "Cartomancien{" + super.toString() + '}';
    }
    
}
