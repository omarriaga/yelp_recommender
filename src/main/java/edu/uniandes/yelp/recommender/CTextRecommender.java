/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.yelp.recommender;

import edu.uniande.yelp.entities.Business;
import edu.uniande.yelp.entities.Lda;
import edu.uniande.yelp.facades.BusinessService;
import edu.uniande.yelp.facades.LDAService;
import edu.uniande.yelp.facades.ReviewService;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;

/**
 *
 * @author raulandres
 */
@Singleton
public class CTextRecommender {
    @EJB
    private BusinessService businessService;
    @EJB
    private LDAService ldaService;
    private List<Lda> data;
    private MongoDBDataModel dm;
    private List<Long> userIds;

    public CTextRecommender() {
        userIds = new LinkedList<>();
    }
    
    @PostConstruct
    public void config(){
        init();
    }

    public void init() {
        System.out.println("Iniciando MongoDataModel");
    }

   

    public List<Business> recommendation(String userID) {
        List<Lda> items = ldaService.getAllldas(userID);
        List<String> ids = items.stream().map(item -> item.getRecommendation()).collect(Collectors.toList());
        System.out.println("string: "+ids.size());
        return businessService.getBusinessWithStringID(ids);
    }
}
