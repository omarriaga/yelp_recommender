/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.yelp.recommender;

import edu.uniande.yelp.entities.Review;
import edu.uniande.yelp.facades.ReviewService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.recommender101.data.DefaultDataSplitter;

/**
 *
 * @author juan
 */
@Singleton
public class CFRecommender {

    @EJB
    private ReviewService reviewService;
    private List<Review> data;
    
    public void init(){
        data = reviewService.getAllreviews();
        
        
    }
}
