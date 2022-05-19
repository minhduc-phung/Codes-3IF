/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import metier.modele.Client;
import metier.modele.Employe;

/**
 *
 * @author nelouennas
 */
public class Clientdao {
    
    public void creer(Client c){
        JPaUtil.obtenirContextePersistance().persist(c);
    }
    
    public void merge(Client c){
        JPaUtil.obtenirContextePersistance().merge(c);
    }
    
    public Client authentifierClient(String mail){
        String jpql ="select c from Client c where c.mail=:unMail";
        Query q= JPaUtil.obtenirContextePersistance().createQuery(jpql);
        q.setParameter("unMail", mail);
        Client c= (Client) q.getSingleResult();
        return c;
    }
    
    public long compterClients(){        
        String jpql ="select count(c) from Client c ";
        Query q= JPaUtil.obtenirContextePersistance().createQuery(jpql);       
        long total= (long) q.getSingleResult();
        return total;
    }
    
            
}
