package edu.uniandes.yelp.recommender;

import edu.uniande.yelp.entities.Review;
import edu.uniande.yelp.entities.Business;
import edu.uniande.yelp.entities.Tag;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class that offers utilities for using the last.fm dataset on the Recommenders101 framework
 * @author Andres M
 *
 */
public class ContentBasedUtilities {
	
	/**
	 * Creates a feature-weight file for the recommenders101 framework content-based recommender <br>
	 * @param inputFile
	 * @param outpurFile
	 */
	public static void createFeatureWeightFile(String inputFile, String outpurFile){
		
		BufferedReader reed=null;
		PrintWriter pr= null;
		
		HashMap<String,HashMap<String,Integer>> itemId_featureId_count= new HashMap<>();
		try {
			reed= new BufferedReader(new FileReader(inputFile));
			
			pr= new PrintWriter(new File(outpurFile));
			
			String line=null;
			
			reed.readLine();//header
			while((line=reed.readLine())!=null){
				String[] splitted=line.split("\t");
				String itemId = splitted[1];
				String featureId=splitted[2];
				
				
				if(!itemId_featureId_count.containsKey(itemId)){
					itemId_featureId_count.put(itemId, new HashMap<String,Integer>());
				}
				
				HashMap<String,Integer> itemHashmap = itemId_featureId_count.get(itemId);
				if(!itemHashmap.containsKey(featureId)){
					itemHashmap.put(featureId, 1);
				}
				else{
					itemHashmap.put(featureId, itemHashmap.get(featureId)+1);
				}
				
				
			}
			
			for (String itemId : itemId_featureId_count.keySet()) {
				HashMap<String,Integer> itemHashmap = itemId_featureId_count.get(itemId);
				for (String featureId  : itemHashmap.keySet()) {
					// item-id;feature-id:weight feature-id weight
					Integer weight = itemHashmap.get(featureId);
					if (weight > 3) {
						String outputLine = itemId + ";" + featureId + ":"
								+ weight;

						pr.println(outputLine);
					}
					
				}
				
			}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(reed!=null)
				try {reed.close();} 
			catch (IOException e) {}
			if(pr!=null){
				pr.close();
			}
		}
		
		
	}
	
	public static List<Tag> createFeatureWeightFile(List<Business> data){
		
            HashMap<String,HashMap<String,Integer>> itemId_featureId_count= new HashMap<>();
			
            for(Business rev : data){
                String itemId = rev.getBusinessId();
                List<String> featureId= rev.getTagsId();

                HashMap<String,Integer> itemHashmap = itemId_featureId_count.get(itemId);
                for(int i = 0; i < featureId.size(); i++){
                    itemHashmap.put(featureId.get(i), i+1);
                }
                
                itemId_featureId_count.put(itemId, itemHashmap);

            }
            List<Tag> tags = new ArrayList<>();
            for (String itemId : itemId_featureId_count.keySet()) {
                    HashMap<String,Integer> itemHashmap = itemId_featureId_count.get(itemId);
                    for (String featureId  : itemHashmap.keySet()) {
                            // item-id;feature-id:weight feature-id weight
                            Integer weight = itemHashmap.get(featureId);
                            if (weight > 3) {
                                    Tag newTag = new Tag();
                                    newTag.setFeatureId(featureId);
                                    newTag.setItemId(itemId);
                                    newTag.setWeight(weight);
                                    tags.add(newTag);
                            }
                    }
            }	
            return tags;
	}

}
