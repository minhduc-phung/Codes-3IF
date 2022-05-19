/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Medium;

/**
 *
 * @author nelouennas
 */
public class Employedao {

    public void creer(Employe e) {

        JPaUtil.obtenirContextePersistance().persist(e);
    }

    public void merge(Employe e) {
        JPaUtil.obtenirContextePersistance().merge(e);
    }

    public Employe affectationEmploye(Medium m) {
        String genre = m.getGenre();
        String jpql = "select e from Employe e where e.genre=:genre and e.disponibilite=true";
        Query q = JPaUtil.obtenirContextePersistance().createQuery(jpql);
        q.setParameter("genre", genre);
        List<Employe> e = (List<Employe>) q.getResultList();
        Employe emp = null;
        if (!e.isEmpty()) {
            int min = e.get(0).getHistoriqueConsultations().size();
            int position = 0;
            for (int i = 0; i < e.size(); i++) {
                int nbConsultations = e.get(i).getHistoriqueConsultations().size();
                if (nbConsultations < min) {
                    min = nbConsultations;
                    position = i;
                }
            }
            emp = e.get(position);
        }
        return emp;

    }

    public Employe authentifierEmploye(String mail) {
        String jpql = "select c from Employe c where c.mail=:unMail";
        Query q = JPaUtil.obtenirContextePersistance().createQuery(jpql);
        q.setParameter("unMail", mail);
        Employe e = (Employe) q.getSingleResult();
        return e;
    }
    
    public long NbClientsParEmploye(Employe e){        
        String jpql ="select count(c) from Client c,Consultation con, Employe e where c.id=con.client.id and e.id=con.employe.id and e.nom=:unNom and e.prenom=:unPrenom";
        Query q= JPaUtil.obtenirContextePersistance().createQuery(jpql);       
        q.setParameter("unNom", e.getNom());
        q.setParameter("unPrenom", e.getPrenom());
        long total= (long) q.getSingleResult();
        return total;
    }
    
    public List<Employe> listerEmployes(){
        String jpql ="select e from Employe e";
       Query q= JPaUtil.obtenirContextePersistance().createQuery(jpql);
        List<Employe> e = (List<Employe>) q.getResultList();
        return e;
    }

}
