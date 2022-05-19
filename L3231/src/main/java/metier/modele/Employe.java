package metier.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nelouennas
 */
@Entity
public class Employe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String genre;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String mail;
    private String motDePasse;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    private String numTelephone;
    private Boolean disponibilite;
    @OneToMany(mappedBy="employe")
    private List<Consultation> historiqueConsultations = new ArrayList<>();
    

    protected Employe() {
    }

    public Employe(String civilite, String nom, String prenom, Date dateNaissance, String numTelephone, String mail, String motDePasse) {
        this.genre = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.dateNaissance = dateNaissance;
        this.numTelephone = numTelephone;
        this.disponibilite=true;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public Boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public List<Consultation> getHistoriqueConsultations() {
        return historiqueConsultations;
    }

    public void setHistoriqueConsultations(List<Consultation> historiqueConsultations) {
        this.historiqueConsultations = historiqueConsultations;
    }
    
    public void addConsultation(Consultation consultation){
        this.historiqueConsultations.add(consultation);
        if (consultation.getEmploye() != this){
            consultation.setEmploye(this);
        }
    }
    
    public void removeConsultation(Consultation consultation){
        this.historiqueConsultations.remove(consultation);
        consultation.setEmploye(null);
    }

    @Override
    public String toString() {
        return "Employe{" + "id=" + id + ", civilite=" + genre + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", motDePasse=" + motDePasse + ", dateNaissance=" + dateNaissance + ", numTelephone=" + numTelephone + ", disponibilite=" + disponibilite + '}';
    }

    
   

}
