/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 *
 * @author nelouennas
 */
@Entity
@Inheritance (strategy=InheritanceType.JOINED)
public class Medium implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String genre;
    private String denomination;
    private String presentation;
    @OneToMany(mappedBy="medium")
    private List<Consultation> historiqueConsultations = new ArrayList<>();

    public Medium() {
    }

    public Medium(String genre, String denomination, String presentation) {
        this.denomination = denomination;
        this.genre = genre;
        this.presentation = presentation;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }
    
    public List<Consultation> getHistoriqueConsultations() {
        return historiqueConsultations;
    }

    public void setHistoriqueConsultations(List<Consultation> historiqueConsultations) {
        this.historiqueConsultations = historiqueConsultations;
    }
    
    public void addConsultation(Consultation consultation){
        this.historiqueConsultations.add(consultation);
        if (consultation.getMedium() != this){
            consultation.setMedium(this);
        }
    }
    
    public void removeConsultation(Consultation consultation){
        this.historiqueConsultations.remove(consultation);
        consultation.setMedium(null);
    }

    
    @Override
    public String toString() {
        return  "{ denomination=" + denomination + '}';
    }

    
}
