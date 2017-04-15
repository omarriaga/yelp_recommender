/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.yelp.evaluator;

import edu.uniandes.yelp.recommender.CFRecommender;
import edu.uniandes.yelp.recommender.MongoDBDataModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.recommenders.rival.core.DataModel;
import net.recommenders.rival.core.DataModelIF;
import net.recommenders.rival.core.TemporalDataModel;
import net.recommenders.rival.split.splitter.CrossValidationSplitter;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.model.Preference;

/**
 *
 * @author juan
 */
public class CFRecommenderEvaluator {
    
    /**
     * Default number of folds.
     */
    public static final int N_FOLDS = 5;
    /**
     * Default neighbohood size.
     */
    public static final int NEIGH_SIZE = 50;
    /**
     * Default cutoff for evaluation metrics.
     */
    public static final int AT = 10;
    /**
     * Default relevance threshold.
     */
    public static final double REL_TH = 3.0;
    /**
     * Default seed.
     */
    public static final long SEED = 2048L;
    
    private MongoDBDataModel dm;
    private DataModelIF<Long, Long>[] splits;
    
    public void prepareData() {
        try {
            DataModel<Long, Long> rival = new TemporalDataModel<>();
            LongPrimitiveIterator iterator = dm.getUserIDs();
            while(iterator.hasNext()){
                for (Preference p : dm.getPreferencesFromUser(iterator.nextLong())) {
                    rival.addPreference(p.getUserID(), p.getItemID(), (double)p.getValue());
                }
            }
            CrossValidationSplitter<Long, Long> splitter;
            splitter = new CrossValidationSplitter<>(N_FOLDS,true,SEED);
            splits = splitter.split(rival);
        } catch (TasteException ex) {
            Logger.getLogger(CFRecommender.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
}
