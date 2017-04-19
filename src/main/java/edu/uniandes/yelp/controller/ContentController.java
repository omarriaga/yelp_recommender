/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.yelp.controller;

import edu.uniandes.yelp.recommender.ContentRecommender;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author juan
 */
@Named(value = "contentController")
@ViewScoped
public class ContentController implements Serializable {
    
    @EJB
    private ContentRecommender contentRecommender;

    /**
     * Creates a new instance of ContentController
     */
    public ContentController() {
    }
    
    public void recommend(){
        try {
            contentRecommender.init();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
