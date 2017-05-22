/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniande.yelp.facades;

import edu.uniande.yelp.entities.Lda;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author raulandres
 */
@Stateless
public class LDAService {
    @PersistenceContext(unitName = "yelp_recommenderPU")
    private EntityManager em;
    
    
    public List<Lda> getAllldas(String Id){
         //dejar esta vaiable para escoger
        List<Lda> ldas = em.createNativeQuery("{user_id: \""+Id+"\" }", Lda.class).getResultList();
        return ldas;
    }
}
