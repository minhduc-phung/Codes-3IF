/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import metier.modele.Client;
import metier.modele.Medium;

/**
 *
 * @author nelouennas
 */
public class Mediumdao {
    
    public void creer(Medium m){
        JPaUtil.obtenirContextePersistance().persist(m);
    }
    
    public void merge(Medium m){
        JPaUtil.obtenirContextePersistance().merge(m);
    }
    
    public List<Medium> consulterListeMediums(){
        String jpql ="select m from Medium m";
        Query q= JPaUtil.obtenirContextePersistance().createQuery(jpql);
        List<Medium> m = (List<Medium>) q.getResultList();
        return m;
    }
               
}
