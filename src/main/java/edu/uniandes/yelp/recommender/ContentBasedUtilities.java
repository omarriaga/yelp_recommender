package edu.uniandes.yelp.recommender;

import edu.uniande.yelp.entities.Business;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that offers utilities for using the last.fm dataset on the
 * Recommenders101 framework
 *
 * @author Andres M
 *
 */
public class ContentBasedUtilities {

    /**
     * Creates a feature-weight file for the recommenders101 framework
     * content-based recommender <br>
     *
     * @param inputFile
     * @param outpurFile
     */
    public static void createFeatureWeightFile(String inputFile, String outpurFile) {

        BufferedReader reed = null;
        PrintWriter pr = null;

        HashMap<String, HashMap<String, Integer>> itemId_featureId_count = new HashMap<>();
        try {
            reed = new BufferedReader(new FileReader(inputFile));

            pr = new PrintWriter(new File(outpurFile));

            String line = null;

            reed.readLine();//header
            while ((line = reed.readLine()) != null) {
                String[] splitted = line.split("\t");
                String itemId = splitted[1];
                String featureId = splitted[2];

                if (!itemId_featureId_count.containsKey(itemId)) {
                    itemId_featureId_count.put(itemId, new HashMap<String, Integer>());
                }

                HashMap<String, Integer> itemHashmap = itemId_featureId_count.get(itemId);
                if (!itemHashmap.containsKey(featureId)) {
                    itemHashmap.put(featureId, 1);
                } else {
                    itemHashmap.put(featureId, itemHashmap.get(featureId) + 1);
                }

            }

            for (String itemId : itemId_featureId_count.keySet()) {
                HashMap<String, Integer> itemHashmap = itemId_featureId_count.get(itemId);
                for (String featureId : itemHashmap.keySet()) {
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
        } finally {
            if (reed != null) {
                try {
                    reed.close();
                } catch (IOException e) {
                }
            }
            if (pr != null) {
                pr.close();
            }
        }

    }

    public static void createFeatureWeightFile(List<Business> data) {

        PrintWriter pr = null;
        PrintWriter prBusiness = null;
        try {
            pr = new PrintWriter(new File("/Users/juan/tag_weight.txt"));
            prBusiness = new PrintWriter(new File("/Users/juan/business.dat"));

            HashMap<String, Integer> weightTags = new HashMap<>();
            for (Business rev : data) {

                String outputBusiness = rev.getBusinessId() + "\t" + rev.getName() + "\t" + rev.getCity() + "\t"
                        + rev.getNeighborhood() + "\t" + rev.getState() + "\t" + rev.getAddress();
                prBusiness.println(outputBusiness);

                String itemId = rev.getBusinessId();
                List<String> featureId = rev.getCategories();
                System.out.println("size: " + featureId.size());
                featureId.stream().forEach((String tag) -> {
                    if (!weightTags.containsKey(tag)) {
                        weightTags.put(tag, 1);
                    } else {
                        weightTags.put(tag, weightTags.get(tag) + 1);
                    }
                });
            }

            // List<Tag> tags = new ArrayList<>();
            for (Business rev : data) {
                List<String> businessTags = rev.getCategories();
                for (String tag : businessTags) {
                    //Tag newTag = new Tag();
                    //newTag.setFeatureId(tag);
                    //newTag.setItemId(rev.getBusinessId());
                    //newTag.setWeight(weightTags.get(tag));
                    if (weightTags.get(tag) > 3) {
                        //tags.add(newTag);
                        String outputLine = rev.getBusinessId() + ";" + tag + ":" + weightTags.get(tag);
                        pr.println(outputLine);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContentBasedUtilities.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pr.close();
            prBusiness.close();
        }

        //return tags;
    }

}
