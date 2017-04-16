/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.yelp.evaluator;

import org.recommender101.data.DataModel;
import org.recommender101.eval.metrics.Precision;
import org.recommender101.eval.metrics.Recall;
import org.recommender101.recommender.baseline.NearestNeighbors;

/**
 *
 * @author juan
 */
public class CFRecommenderEvaluator {
    
    private DataModel model_test;
    private DataModel model_train;
    private NearestNeighbors recommender;
    
    public void prepareData() {


    }
    
    public void init(){
        Precision precision_metric = new Precision();
        precision_metric.setRecommender(recommender);
        precision_metric.setTestDataModel(model_test);
        precision_metric.setTrainingDataModel(model_train);
        precision_metric.initialize();
        precision_metric.setTargetSet("allrelevantintestset");

        //System.out.println("precision: "+precision_metric.getEvaluationResult());

        Recall recall_metric = new Recall();
        recall_metric.setRecommender(recommender);
        recall_metric.setTestDataModel(model_test);
        recall_metric.setTrainingDataModel(model_train);
        recall_metric.initialize();
        recall_metric.setTargetSet("allrelevantintestset");
    }
    
    
    
    
}
