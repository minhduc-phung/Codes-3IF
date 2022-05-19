/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author nelouennas
 */
@Entity
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String numero;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateHeureDebutAppel;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateHeureFinAppel;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateHeureDemande;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Employe employe;
    @ManyToOne
    private Medium medium;
    private String commentaire;

    

    public Consultation(Client client, Employe employe, Medium medium, Date dateHeureDemande) {
        this.dateHeureDemande = dateHeureDemande;
        this.client = client;
        this.employe = employe;
        this.medium = medium;
    }

    public Consultation() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Consultation(Client c, Medium m, Employe e, Date dateDemande) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDateHeureDebutAppel() {
        return dateHeureDebutAppel;
    }

    public void setDateHeureDebutAppel(Date dateHeureDebutAppel) {
        this.dateHeureDebutAppel = dateHeureDebutAppel;
    }

    public Date getDateHeureFinAppel() {
        return dateHeureFinAppel;
    }

    public void setDateHeureFinAppel(Date dateHeureFinAppel) {
        this.dateHeureFinAppel = dateHeureFinAppel;
    }

    public Date getDateHeureDemande() {
        return dateHeureDemande;
    }

    public void setDateHeureDemande(Date dateHeureDemande) {
        this.dateHeureDemande = dateHeureDemande;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Consultation{" + "numero=" + numero + ", dateHeureDebutAppel=" + dateHeureDebutAppel + ", dateHeureFinAppel=" + dateHeureFinAppel + ", dateHeureDemande=" + dateHeureDemande + ", client=" + client + ", employe=" + employe + ", medium=" + medium + '}';
    }

    

}
