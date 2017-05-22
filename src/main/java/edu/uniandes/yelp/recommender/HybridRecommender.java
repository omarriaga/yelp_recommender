/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.yelp.recommender;

import javax.ejb.EJB;
import javax.ejb.Singleton;

/**
 *
 * @author juan
 */
@Singleton
public class HybridRecommender {
    
    @EJB
    private CFRecommender cfrecommender;
    @EJB
    private ContentRecommender contentRecommender;
    @EJB
    private CTextRecommender textRecommender;

    public HybridRecommender() {
    }
    
    public void recommend(){
        
    }
    
    
    
    
    
}
