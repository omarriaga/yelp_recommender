/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniande.yelp.facades;

import edu.uniande.yelp.entities.Business;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.bson.types.ObjectId;

/**
 *
 * @author juan
 */
@Stateless
public class BusinessService {

    @PersistenceContext(unitName = "yelp_recommenderPU")
    private EntityManager em;
    
    
    public List<Business> getAllBusiness(){
        return em.createNativeQuery("{}", Business.class).getResultList();
    }
    
    
    public List<Business> getBusiness(List<ObjectId> ids){
        String ids_string = ids.stream().map(id -> "{ \"$oid\":  \""+id.toHexString()+"\"}").collect(Collectors.joining(","));
        System.out.println("strings: "+ids_string);
        Query q = em.createNativeQuery("{_id : { $in: ["+ids_string+"] }}", Business.class);
        return q.getResultList();
    }
    
    public List<Business> getBusinessWithStringID(List<String> ids){
        String ids_string = ids.stream().collect(Collectors.joining(","));
        //System.out.println("strings: "+ids_string);
        Query q = em.createNativeQuery("{recommendation : { $in: ["+ids_string+"] }}", Business.class);
        System.out.println("termino query");
        return q.getResultList().subList(0, 30);
    }
    
    public void getReview(){
        System.out.println("iniciando consulta");
        List<Business> reviews = em.createNativeQuery("{business_id: '2aFiy99vNLklCx3T_tGS9A'}", Business.class).getResultList();
        System.out.println("size: "+reviews.size());
        for(Business review : reviews){
            System.out.println("review.text: "+review.getBusinessId());
            System.out.println("review.tags: "+review.getCategories().stream().collect(Collectors.joining(",")));
            System.out.println("====================================================");
        }
    }
    
    
    
}
