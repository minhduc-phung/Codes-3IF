/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import javax.persistence.Entity;

/**
 *
 * @author nelouennas
 */
@Entity
public class Astrologue extends Medium {

    private String formation;
    private String promotion;

    public Astrologue(String genre, String denomination, String presentation, String formation, String promotion) {
        super(genre, denomination, presentation);
        this.formation = formation;
        this.promotion = promotion;
    }

    

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public Astrologue() {
    }
/*
    @Override
    public String toString() {
        return "Astrologue{" +super.toString()+ "formation=" + formation + ", promotion=" + promotion + '}';
    }*/

    
    
    
    
    
}
