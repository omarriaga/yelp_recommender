/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.yelp.controller;

import edu.uniande.yelp.entities.Business;
import edu.uniandes.yelp.recommender.CTextRecommender;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author juan
 */
@Named(value = "textController")
@ViewScoped
public class TextController implements Serializable{

    @EJB
    private CTextRecommender textRecommender;
    private String userId;
    private List<Business> items;
        /**
     * Creates a new instance of TextController
     */
    public TextController() {
    }
    
    @PostConstruct
    public void init(){
        try {
            //cfrecommender.init();
           
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recommend(){
       items = textRecommender.recommendation(userId);
        System.out.println("items"+ items.size());
       items.stream().forEach((item) -> {
           System.out.println("item: "+item.getName());
        });
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Business> getItems() {
        return items;
    }

    public void setItems(List<Business> items) {
        this.items = items;
    }
    
}
