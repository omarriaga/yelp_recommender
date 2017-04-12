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
import org.recommender101.data.*;
import org.recommender101.recommender.baseline.NearestNeighbors;

/**
 *
 * @author juan
 */
@Singleton
public class CFRecommender {

    @EJB
    private ReviewService reviewService;
    private List<Review> data;

    public void init() {
        try {
            data = reviewService.getAllreviews();
            DataModel train_model = new DataModel();
            DataModel test_model = new DataModel();
            int i = 0;
            for (Review review : data) {
                train_model.addRating(review.getnUserId(), review.getnBusinessId(), review.getStars());
            }
            System.out.println("train_model size: " + train_model.getRatings().size());
            System.out.println("test_model size: " + test_model.getRatings().size());
            NearestNeighbors recommender = new NearestNeighbors();
            recommender.setItemBased("true");
            recommender.setDataModel(train_model);
            recommender.setNeighbors("30");
            recommender.setSimThreshold("0.7");
            recommender.init();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
