/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.yelp.controller;

import edu.uniande.yelp.facades.ReviewService;
import edu.uniande.yelp.facades.TipsService;
import edu.uniandes.yelp.recommender.CFRecommender;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
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
    private CFRecommender cfrecommender;
    //Lista de usuarios
    private List<Long> userIds;
    private long userId;

    /**
     * Creates a new instance of HomeController
     */
    public HomeController() {
    }
    
    @PostConstruct
    public void init(){
        cfrecommender.init();
        userIds = cfrecommender.getUserIds();
    }


    public String hello(){
        //tipsService.getTip();
        //reviewService.getAllreviews();
        //cfrecommender.init();
        return "hello World!!";
    }
    
    public List<Long> limitedList(){
        return userIds.subList(0, 50);
    }
    
    public void recommend(){
        cfrecommender.recommend(userId);
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
     
}
