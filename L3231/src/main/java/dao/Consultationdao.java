/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import metier.modele.Consultation;

/**
 *
 * @author nelouennas
 */
public class Consultationdao {
    
    public void creer(Consultation c){
        JPaUtil.obtenirContextePersistance().persist(c);
    }
    
    public void merge(Consultation c){
        JPaUtil.obtenirContextePersistance().merge(c);
    }
}
