/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.yelp.recommender;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.recommender101.data.DataModel;
import org.recommender101.recommender.AbstractRecommender;
import org.recommender101.recommender.extensions.contentbased.ContentBasedRecommender;

/**
 *
 * @author Usuario
 */
public class ContentRecommender extends AbstractRecommender{
    private DataModel model;
	private ContentBasedRecommender contentBasedRecommender;

	@Override
	public float predictRating(int user, int item) {
		return 0;
	}

	@Override
	public List<Integer> recommendItems(int user) {
		return new LinkedList<>();
	}
	
	public static void main(String[] args) throws Exception{
		ContentRecommender cr = new ContentRecommender();
		cr.init();
	}

	@Override
	public void init() throws Exception {
		
		this.model = new DataModel();
                model.addRating(0, 0, 0);
		
		//Ya fue pre-procesado
		//ContentBasedUtilities.createFeatureWeightFile("data/user_taggedartists.dat", "data/tag_weight.txt");
		this.contentBasedRecommender= new ContentBasedRecommender();
		ContentBasedRecommender.dataDirectory="data";
		contentBasedRecommender.setDataModel(model);
		contentBasedRecommender.setWordListFile("artists.dat");
		//La implementaciï¿½n crea unos vectores de similitud, que guarda en el archivo cos-sim-vectors.txt, ya fueron calculados
		contentBasedRecommender.setFeatureWeightFile("tag_weight.txt");
		contentBasedRecommender.init();

		List<Integer> lista = contentBasedRecommender.recommendItems(2).subList(0, 500);
		List<Integer> resp = new ArrayList();;
	}
}
