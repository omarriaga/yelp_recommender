/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.yelp.controller;

import edu.uniande.yelp.facades.LDAService;
import edu.uniande.yelp.facades.ReviewService;
import edu.uniande.yelp.facades.TipsService;
import edu.uniandes.yelp.recommender.CFRecommender;
import edu.uniandes.yelp.recommender.ContentRecommender;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author juan
 */
@ManagedBean
@Named(value = "homeController")
@ViewScoped
public class HomeController implements Serializable{
    
    @EJB
    private TipsService tipsService;
    @EJB
    private ReviewService reviewService;
    @EJB
    private CFRecommender cfrecommender;
    @EJB
    private ContentRecommender contentRecommender;
    @EJB
    private LDAService ldaService;

    /**
     * Creates a new instance of HomeController
     */
    public HomeController() {
    }


    public String hello(){
        try {
            //tipsService.getTip();
            //reviewService.getReview();
            //cfrecommender.init();
            //contentRecommender.init();
            ldaService.getLda();
            
            
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "hello World!!";
    }
    
}
