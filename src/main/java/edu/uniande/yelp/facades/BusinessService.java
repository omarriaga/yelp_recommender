/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniande.yelp.facades;

import edu.uniande.yelp.entities.Business;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author juan
 */
@Stateless
public class BusinessService {

    @PersistenceContext(unitName = "yelp_recommenderPU")
    private EntityManager em;
    
    
    public List<Business> getAllBusiness(){
        return em.createNativeQuery("{}", Business.class).getResultList();
    }
    
    
    public void getReview(){
        List<Business> reviews = em.createNativeQuery("{business_id: '2aFiy99vNLklCx3T_tGS9A'}", Business.class).getResultList();
        for(Business review : reviews){
            System.out.println("review.text: "+review.getBusinessId());
            System.out.println("review.stars: "+review.getTagsId());
            System.out.println("====================================================");
        }
    }
    
}
