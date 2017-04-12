/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniande.yelp.facades;

import edu.uniande.yelp.entities.Review;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author juan
 */
@Stateless
public class ReviewService {

    @PersistenceContext(unitName = "yelp_recommenderPU")
    private EntityManager em;
    
    
    public List<Review> getAllreviews(){
        List<Review> reviews = em.createNativeQuery("{}", Review.class).getResultList();
        return reviews;
    }
    
    
    public void getReview(){
        List<Review> reviews = em.createNativeQuery("{business_id: '2aFiy99vNLklCx3T_tGS9A'}", Review.class).getResultList();
        for(Review review : reviews){
            System.out.println("review.text: "+review.getText());
            System.out.println("review.stars: "+review.getStars());
            System.out.println("====================================================");
        }
    }
    
}
