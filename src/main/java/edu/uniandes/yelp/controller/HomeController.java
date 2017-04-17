/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.yelp.controller;

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
    private CFRecommenderInt cfrecommender;
    //Lista de usuarios
    private List<Integer> userIds;
    private int userId;

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


    public String hello(){
        //tipsService.getTip();
        //reviewService.getAllreviews();
        //cfrecommender.init();
        return "hello World!!";
    }
    
    public List<Integer> limitedList(){
        return userIds.subList(0, 50);
    }
    
    public void recommend(){
       List<Integer> items = cfrecommender.recommendItems(userId);
       items.stream().forEach((item) -> {
           System.out.println("item: "+item);
        });
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
     
}
