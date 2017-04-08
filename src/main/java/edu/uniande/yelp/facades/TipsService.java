/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniande.yelp.facades;

import edu.uniande.yelp.entities.Tip;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author juan
 */
@Stateless
public class TipsService {

    @PersistenceContext(unitName = "yelp_recommenderPU")
    private EntityManager em;
    
    
    public void getTip(){
        List<Tip> tips = em.createNativeQuery("{}", Tip.class ).getResultList();
        for (Tip tip: tips){
            System.out.println("tip.text: "+tip.getText());
        }
    }
    
}
