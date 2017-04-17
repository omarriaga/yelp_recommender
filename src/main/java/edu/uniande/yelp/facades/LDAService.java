/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniande.yelp.facades;

import edu.uniande.yelp.entities.Lda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author raulandres
 */
public class LDAService {
    @PersistenceContext(unitName = "yelp_recommenderPU")
    private EntityManager em;
    
    
    public List<Lda> getAllldas(){
        return em.createNativeQuery("{}", Lda.class).getResultList();
    }
    
    
    public void getLda(){
        List<Lda> ldas = em.createNativeQuery("{business_id: '2aFiy99vNLklCx3T_tGS9A'}", Lda.class).getResultList();
        for(Lda lda : ldas){
            System.out.println("LDA_1: "+lda.getLDA_1());
            System.out.println("LDA_2: "+lda.getLDA_2());
            System.out.println("====================================================");
        }
    }
}
