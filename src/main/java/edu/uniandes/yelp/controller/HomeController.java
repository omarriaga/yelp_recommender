/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.yelp.controller;

import edu.uniande.yelp.entities.Business;
import edu.uniande.yelp.facades.BusinessService;
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
@Named(value = "homeController")
@ViewScoped
public class HomeController implements Serializable{
    
    @EJB
    private BusinessService businessService;

    /**
     * Creates a new instance of HomeController
     */
    public HomeController() {
    }
    
    @PostConstruct
    public void init(){
        try {
            businessService.getReview();
        } catch (Exception ex) {
            System.out.println("boom!!");
        }
    }
 
    public String hello(){
        return "hello World!!";
    }
    
}
