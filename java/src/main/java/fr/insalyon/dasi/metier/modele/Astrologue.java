/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Nicolas
 */
@Entity
public class Astrologue extends Medium implements Serializable{
    private String formation;
    private Integer promo;

    public Astrologue() {
    }

    public Astrologue(String formation, Integer promo) {
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
    
}
