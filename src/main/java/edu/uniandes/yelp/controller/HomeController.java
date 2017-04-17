/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.yelp.controller;

import edu.uniandes.yelp.recommender.CFRecommender;
import edu.uniandes.yelp.recommender.CFRecommenderInt;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Long userId;

    /**
     * Creates a new instance of HomeController
     */
    public HomeController() {
    }
    
    @PostConstruct
    public void init(){
        try {
            cfrecommender.init();
            userIds = new LinkedList<>(cfrecommender.getUserIds());
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public List<Long> limitedList(){
        return userIds.subList(0, 50);
    }
    
    public void recommend(){
       List<Long> items = cfrecommender.recommend(userId);
       items.stream().forEach((item) -> {
           System.out.println("item: "+item);
        });
    }
    
    public void eval(){
        cfrecommender.evaluate();
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
     
}
