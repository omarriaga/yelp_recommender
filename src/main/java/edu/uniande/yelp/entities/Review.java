/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniande.yelp.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.bson.types.ObjectId;

/**
 *
 * @author juan
 */
@Entity
@Table(name = "reviews")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ObjectId id;
    @Column(name = "review_id")
    private String reviewId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "business_id")
    private String businessId;
    @Column(name = "stars")
    private int stars;
    @Column(name = "date")
    private String date;
    @Column(name = "text")
    private String text;
    @Column(name = "useful")
    private int useful;
    @Column(name = "funny")
    private int funny;
    @Column(name = "cool")
    private int cool;
    @Column(name = "type")
    private String type;
    @Transient
    private int nUserId;
    @Transient
    private int nBusinessId;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        nUserId = transform(userId);
        this.userId = userId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        nBusinessId = transform(businessId);
        this.businessId = businessId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUseful() {
        return useful;
    }

    public void setUseful(int useful) {
        this.useful = useful;
    }

    public int getFunny() {
        return funny;
    }

    public void setFunny(int funny) {
        this.funny = funny;
    }

    public int getCool() {
        return cool;
    }

    public void setCool(int cool) {
        this.cool = cool;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getnUserId() {
        return nUserId;
    }

    public void setnUserId(int nUserId) {
        this.nUserId = nUserId;
    }

    public int getnBusinessId() {
        return nBusinessId;
    }

    public void setnBusinessId(int nBusinessId) {
        this.nBusinessId = nBusinessId;
    }
    
    public static int transform(String name) {
        String a = "";
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) >= 57 || name.charAt(i) < 48) {
                a += (int) name.charAt(i);
            } else {
                a += name.charAt(i);
            }
        }
        try {
            return Integer.parseInt(a);
        } catch (NumberFormatException e) {
            System.out.println(name + " " + a);
            return 0;
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.uniande.yelp.entities.Review[ id=" + id + " ]";
    }

}
