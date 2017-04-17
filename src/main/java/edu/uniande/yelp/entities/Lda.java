/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniande.yelp.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderColumn;
import org.bson.types.ObjectId;

/**
 *
 * @author raulandres
 */
public class Lda {
    private static final long serialVersionUID = 1L;
    @Column(name = "business_id")
    private String businessId;
    @Column(name = "lda_1")
    private String lda_1;
    @Column(name = "lda_2")
    private String lda_2;

    public String getbusinessId() {
        return businessId;
    }

    public void setbusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getLDA_1() {
        return lda_1;
    }

    public void setLDA_1(String lda_1) {
        this.lda_1 = lda_1;
    }
    
    public String getLDA_2() {
        return lda_2;
    }

    public void setLDA_2(String lda_2) {
        this.lda_2 = lda_2;
    }
}
