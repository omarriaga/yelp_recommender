/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.yelp.recommender;

import edu.uniande.yelp.entities.Lda;
import edu.uniande.yelp.facades.LDAService;
import java.util.List;
import javax.ejb.EJB;
import org.recommender101.data.DataModel;

/**
 *
 * @author raulandres
 */
public class CTextRecommender {
     @EJB
    private LDAService ldaService;
    private List<Lda> data;
    
    
    public void init(){
        try {
            data = ldaService.getAllldas();
            DataModel train_model = new DataModel();
            DataModel test_model = new DataModel();
            
            /*StringMetric metric = StringMetrics.cosineSimilarity();
	
            float result = metric.compare(str1, str2);
            for (Lda lda : data){
                if (i%5==0){
                    test_model.addRating(lda.getbusinessId(), lda.getLDA_1(), lda.getLDA_2());
                }else{
                    train_model.addRating(review.getnUserId(), review.getnBusinessId(), review.getStars());
                }
            }*/
            
          //  recommender.init();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
