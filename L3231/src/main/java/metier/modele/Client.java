package metier.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String civilite;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String mail;
    private String motDePasse;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    private String numTelephone;
    @Embedded
    private ProfilAstral profilAstral;
    @OneToMany(mappedBy="client")
    private List<Consultation> historiqueConsultations = new ArrayList();

    protected Client() {
    }

    public Client(String civilite, String nom, String prenom, Date dateNaissance, String numTelephone, String mail, String motDePasse) {
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.dateNaissance = dateNaissance;
        this.numTelephone = numTelephone;
        this.profilAstral=new ProfilAstral();
    }

    public Long getId() {
        return id;
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

    public String getNumTelephone() {
        return numTelephone;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public ProfilAstral getProfilAstral() {
        return profilAstral;
    }

    public void setProfilAstral(ProfilAstral profilAstral) {
        this.profilAstral = profilAstral;
    }
    
    public void addConsultation(Consultation consultation){
    this.historiqueConsultations.add(consultation);
        if (consultation.getClient() != this){
            consultation.setClient(this);
        }
    }
    
    public void removeConsultation(Consultation consultation){
        this.historiqueConsultations.remove(consultation);
        consultation.setClient(null);
    }

    public List<Consultation> getHistoriqueConsultations() {
        return historiqueConsultations;
    }
    

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", motDePasse=" + motDePasse + ", dateNaissance=" + dateNaissance + ", numTelephone=" + numTelephone + ", profilAstral=" + profilAstral + '}';
    }

}
