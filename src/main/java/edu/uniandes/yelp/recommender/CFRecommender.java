/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.yelp.recommender;

import edu.uniande.yelp.entities.Business;
import edu.uniande.yelp.entities.Review;
import edu.uniande.yelp.facades.BusinessService;
import edu.uniande.yelp.facades.ReviewService;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.bson.types.ObjectId;

/**
 *
 * @author juan
 */
@Singleton
public class CFRecommender {

    @EJB
    private ReviewService reviewService;
    @EJB
    private BusinessService businessService;
    private List<Review> data;
    private GenericItemBasedRecommender recommender;
    private MongoDBDataModel dm;
    private List<Long> userIds;

    public CFRecommender() {
        userIds = new LinkedList<>();
    }
    
    @PostConstruct
    public void config(){
        init();
    }

    public void init() {
        try {
            //data = reviewService.getAllreviews();
            System.out.println("Iniciando MongoDataModel");
            dm = new MongoDBDataModel("localhost", 27017, "yelp", "reviews", true, false, null, "user", "business", "stars", "prueba");
            System.out.println("users: " + dm.getNumUsers());
            System.out.println("items: " + dm.getNumItems());
            ItemSimilarity is = new PearsonCorrelationSimilarity(dm);
            recommender = new GenericItemBasedRecommender(dm, is);

        } catch (UnknownHostException | TasteException ex) {
            ex.printStackTrace();
            System.out.println("exception: " + ex.getLocalizedMessage());
        }
    }

    private void fillUserIds() {
        try {
            userIds = new LinkedList<>();
            LongPrimitiveIterator iterator = dm.getUserIDs();
            while (iterator.hasNext()) {
                userIds.add(iterator.nextLong());
            }
        } catch (TasteException ex) {
            Logger.getLogger(CFRecommender.class.getName()).log(Level.SEVERE, null, ex);
            userIds.clear();
        }
    }

    public List<Long> getUserIds() {
        if (userIds.isEmpty()) {
            fillUserIds();
        }
        return userIds;
    }

    public List<Business> recommend(long userID) {
        try {
            List<RecommendedItem> items = recommender.recommend(userID, 10);
            List<ObjectId> ids = new LinkedList<>();
            System.out.println("user: " + dm.fromLongToId(userID));
            System.out.println("count: " + items.size());
            System.out.println("max preference: " + dm.getMaxPreference());
            System.out.println("min preference: " + dm.getMinPreference());
            for (Preference p : dm.getPreferencesFromUser(userID)) {
                System.out.println("preferencia: item: " + p.getItemID() + " value: " + p.getValue());
            }
            items.stream().forEach((item) -> {
                System.out.println("item : " + item.getItemID());
                ids.add(new ObjectId(dm.fromLongToId(item.getItemID())));
            });
            List<Business> datos = businessService.getBusiness(ids);
            
            return datos;
        } catch (TasteException ex) {
            Logger.getLogger(CFRecommender.class.getName()).log(Level.SEVERE, null, ex);
            return new LinkedList<>();
        }
    }

    public void evaluate() {
        try {
            System.out.println("Iniciando evaluacion");
            RecommenderIRStatsEvaluator evaluator = new GenericRecommenderIRStatsEvaluator();
            System.out.println("Creando constructor");
            RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
                @Override
                public Recommender buildRecommender(DataModel model) throws TasteException {
                    ItemSimilarity is = new PearsonCorrelationSimilarity(model);
                    return  new GenericItemBasedRecommender(model, is);
                }
                
            };
            System.out.println("Iniciando Evaluacion");
            IRStatistics stats = evaluator.evaluate(recommenderBuilder, null, dm, null, 2,
                    GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD, 1.0);
            System.out.println("Precision "+stats.getPrecision());
            System.out.println("Recall "+stats.getRecall());
        } catch (TasteException ex) {
            System.out.println("Boom!!! ");
            ex.printStackTrace();
        }
    }

}
