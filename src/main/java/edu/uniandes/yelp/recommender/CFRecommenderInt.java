/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.yelp.recommender;

import edu.uniande.yelp.entities.Review;
import edu.uniande.yelp.facades.ReviewService;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.recommender101.data.DataModel;
import org.recommender101.recommender.AbstractRecommender;
import org.recommender101.recommender.baseline.NearestNeighbors;

/**
 *
 * @author juan
 */
@Singleton
public class CFRecommenderInt extends AbstractRecommender{

    @EJB
    private ReviewService reviewService;
    private List<Review> data;
    private NearestNeighbors recommender;
    private DataModel model;
    

    @Override
    public void init() throws Exception {
        data = reviewService.getAllreviews();
        model = new DataModel();
        data.stream().forEach((Review r) -> {
            r.initIds();
            System.out.println("user: "+r.getnUserId()+" item: "+r.getnBusinessId() + " value: "+ r.getStars());
            model.addRating(r.getnUserId(), r.getnBusinessId(), r.getStars());
        });
        System.out.println("size: "+model.getMaxUserRatings());
        recommender = new NearestNeighbors();
        recommender.setHideKnownItems("true");
        recommender.setItemBased("true");
        recommender.setMinNeighbors("50");
        recommender.init();
    }

    public Set<Integer> getUserIds() {
        return model.getUsers();
    }

    @Override
    public float predictRating(int user, int item) {
        return recommender.predictRating(user, item);
    }

    @Override
    public List<Integer> recommendItems(int user) {
        return recommender.recommendItems(user);
    }

    

}
