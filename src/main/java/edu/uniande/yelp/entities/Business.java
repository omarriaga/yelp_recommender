/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniande.yelp.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import org.bson.types.ObjectId;

/**
 *
 * @author Usuario
 * 
 * {
   "business_id":"0DI8Dt2PJp07XkVvIElIcQ",
   "name":"Innovative Vapors",
   "neighborhood":"",
   "address":"227 E Baseline Rd, Ste J2",
   "city":"Tempe",
   "state":"AZ",
   "postal_code":"85283",
   "latitude":33.3782141,
   "longitude":-111.936102,
   "stars":4.5,
   "review_count":17,
   "is_open":0,
   "attributes":[
      "BikeParking: True",
      "BusinessAcceptsBitcoin: False",
      "BusinessAcceptsCreditCards: True",
      "BusinessParking: {'garage': False, 'street': False, 'validated': False, 'lot': True, 'valet': False}",
      "DogsAllowed: False",
      "RestaurantsPriceRange2: 2",
      "WheelchairAccessible: True"
   ],
   "categories":[
      "Tobacco Shops",
      "Nightlife",
      "Vape Shops",
      "Shopping"
   ],
   "hours":[
      "Monday 11:0-21:0",
      "Tuesday 11:0-21:0",
      "Wednesday 11:0-21:0",
      "Thursday 11:0-21:0",
      "Friday 11:0-22:0",
      "Saturday 10:0-22:0",
      "Sunday 11:0-18:0"
   ],
   "type":"business"
}
 */
@Entity
@Table(name = "business")
public class Business implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ObjectId id;
    @Column(name = "business_id")
    private String businessId;
    @Column(name = "name")
    private String name;
    @Column(name = "neighborhood")
    private String neighborhood;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "stars")
    private float stars;
    @Column(name = "state")
    private String state;
    @ElementCollection
    @Column(name = "categories")
    private List<String> categories;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public List<String> getCategories() {
        return this.categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }
    
}
