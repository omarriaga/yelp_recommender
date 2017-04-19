/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.yelp.controller;

import edu.uniande.yelp.entities.Business;
import edu.uniandes.yelp.recommender.CFRecommender;
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
@Named(value = "filterController")
@ViewScoped
public class FilterController implements Serializable{
    
    @EJB
    private CFRecommender cfrecommender;
    private List<Long> userIds;
    private Long userId;
    private List<Business> items;

    /**
     * Creates a new instance of FilterController
     */
    public FilterController() {
    }
    
    @PostConstruct
    public void init(){
        try {
            //cfrecommender.init();
            userIds = new LinkedList<>(cfrecommender.getUserIds());
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recommend(){
       items = cfrecommender.recommend(userId);
       items.stream().forEach((item) -> {
           System.out.println("item: "+item.getName());
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
    
    public List<Long> limitedList(){
        return userIds.subList(0, 50);
    }

    public List<Business> getItems() {
        return items;
    }

    public void setItems(List<Business> items) {
        this.items = items;
    }
    
}
