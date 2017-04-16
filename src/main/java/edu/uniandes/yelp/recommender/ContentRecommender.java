/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.yelp.recommender;

import edu.uniande.yelp.entities.Review;
import edu.uniande.yelp.facades.ReviewService;
import edu.uniande.yelp.facades.BusinessService;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.recommender101.data.DataModel;
import org.recommender101.recommender.AbstractRecommender;
import org.recommender101.recommender.extensions.contentbased.ContentBasedRecommender;

/**
 *
 * @author Usuario
 */
@Singleton
public class ContentRecommender extends AbstractRecommender{
    @EJB
    private ReviewService reviewService;
    @EJB
    private BusinessService businessService;
    private List<Review> data;
    private DataModel model;
    private ContentBasedRecommender contentBasedRecommender;

    @Override
    public float predictRating(int user, int item) {
        return 0;
    }

    @Override
    public List<Integer> recommendItems(int user) {
        return new LinkedList<>();
    }

    public static void main(String[] args) throws Exception{
        ContentRecommender cr = new ContentRecommender();
        cr.init();
    }

    @Override
    public void init() throws Exception {

        data = reviewService.getAllreviews();
        
        DataModel train_model = new DataModel();
            DataModel test_model = new DataModel();
            int i = 0;
            for (Review review : data){
                if (i%5==0){
                    test_model.addRating(review.getnUserId(), review.getnBusinessId(), review.getStars());
                }else{
                    
                    train_model.addRating(review.getnUserId(), review.getnBusinessId(), review.getStars());
                }
            }
            
        this.model = train_model;
        
        int user = data.get(0).getnUserId();

        //Ya fue pre-procesado
        ContentBasedUtilities.createFeatureWeightFile(businessService.getAllBusiness());
       
        this.contentBasedRecommender= new ContentBasedRecommender();
        ContentBasedRecommender.dataDirectory="data";
        contentBasedRecommender.setDataModel(model);
        contentBasedRecommender.setWordListFile("business.dat");
        //La implementacion crea unos vectores de similitud, que guarda en el archivo cos-sim-vectors.txt, ya fueron calculados
        contentBasedRecommender.setFeatureWeightFile("tag_weight.txt");
        contentBasedRecommender.init();
        System.out.println("recomendaciones");
        List<Integer> lista = contentBasedRecommender.recommendItems(user).subList(0, 500);
        //List<Integer> resp = new ArrayList(); 
        for (Integer integer : lista) {
            System.out.println("item: " +integer);
        }
    }
}
