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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

/**
 *
 * @author juan
 */
@Singleton
public class CFRecommender {

    @EJB
    private ReviewService reviewService;
    private List<Review> data;
    private GenericItemBasedRecommender recommender;
    private MongoDBDataModel dm;
    private List<Long> userIds;

    public CFRecommender() {
        userIds = new LinkedList<>();
    }
    
    public void init() {
        try {
            //data = reviewService.getAllreviews();
            System.out.println("Iniciando MongoDataModel");
            dm = new MongoDBDataModel("localhost", 27017, "yelp", "reviews", true, false, null, "user", "business", "stars", "prueba");
            System.out.println("users: "+dm.getNumUsers());
            System.out.println("items: " + dm.getNumItems());
            ItemSimilarity is = new PearsonCorrelationSimilarity(dm);
            recommender = new GenericItemBasedRecommender(dm, is);
            
        } catch (UnknownHostException | TasteException ex) {
            ex.printStackTrace();
            System.out.println("exception: " + ex.getLocalizedMessage());
        }
    }
    
    private void fillUserIds(){
        try {
            userIds = new LinkedList<>();
            LongPrimitiveIterator iterator = dm.getUserIDs();
            while(iterator.hasNext()){
                userIds.add(iterator.nextLong());
            }
        } catch (TasteException ex) {
            Logger.getLogger(CFRecommender.class.getName()).log(Level.SEVERE, null, ex);
            userIds.clear();
        }
    }
    
    public List<Long> getUserIds(){
        if(userIds.isEmpty()){
            fillUserIds();
        }
        return userIds;
    }

    public void recommend(long userID) {
        try {
            List<RecommendedItem> items = recommender.recommend(userID, 10);
            System.out.println("user: " + userID);
            System.out.println("count: " + items.size());
            System.out.println("max preference: "+dm.getMaxPreference());
            System.out.println("min preference: "+dm.getMinPreference());
            for (Preference p: dm.getPreferencesFromUser(userID)){
                System.out.println("preferencia: item: "+p.getItemID()+" value: "+p.getValue());
            }
            for (RecommendedItem item : items) {
                System.out.println("item : " + item.getItemID());
            }
        } catch (TasteException ex) {
            Logger.getLogger(CFRecommender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
