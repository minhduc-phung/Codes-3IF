/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Embeddable;
import util.AstroNetApi;

/**
 *
 * @author nelouennas
 */
@Embeddable
public class ProfilAstral {
    private String zodiaque;
    private String astroChinois;
    private String couleurPB;
    private String animalTotem;

    public ProfilAstral() {
    }

    public String getZodiaque() {
        return zodiaque;
    }

    public void setZodiaque(String zodiaque) {
        this.zodiaque = zodiaque;
    }

    public String getAstroChinois() {
        return astroChinois;
    }

    public void setAstroChinois(String astroChinois) {
        this.astroChinois = astroChinois;
    }

    public String getCouleurPB() {
        return couleurPB;
    }

    public void setCouleurPB(String couleurPB) {
        this.couleurPB = couleurPB;
    }

    public String getAnimalTotem() {
        return animalTotem;
    }

    public void setAnimalTotem(String animalTotem) {
        this.animalTotem = animalTotem;
    }

    @Override
    public String toString() {
        return "ProfilAstral{" + "zodiaque=" + zodiaque + ", astroChinois=" + astroChinois + ", couleurPB=" + couleurPB + ", animalTotem=" + animalTotem + '}';
    }

   
}
