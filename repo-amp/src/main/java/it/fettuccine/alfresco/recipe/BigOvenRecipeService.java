package it.fettuccine.alfresco.recipe;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

public class BigOvenRecipeService implements RecipeService {
	Logger logger = Logger.getLogger(BigOvenRecipeService.class);

	private static final int BIG_OVEN_MAX_RECIPE_ID = 250000;
	
	private static final String BIG_OVEN_RECIPE_URL_PREFIX = "http://api.bigoven.com/recipe/";
	
	@Override
	public List<Recipe> search(String recipeKeywords) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Recipe getRotd() {
		
		Recipe recipe = null;
		
		do {
			recipe = retrieveRecipe();
		} while (recipe.getTitle() == null || recipe.getInstructions() == null);
		
		return recipe;
	}

	private Recipe retrieveRecipe() {
		int recipeId = randInt(0, BIG_OVEN_MAX_RECIPE_ID);
		Recipe recipe = new Recipe(recipeId);
		String apiKey = "dvx5S699Gfi7n9BEzsv4vu32aUnTLRv7";
		HttpClient client = new HttpClient();
		String url = BIG_OVEN_RECIPE_URL_PREFIX + recipeId + "?api_key=" + apiKey;
		GetMethod get = new GetMethod(url);
		get.setRequestHeader("Accept", "application/json");
	    try {
			client.executeMethod(get);
			InputStream in = get.getResponseBodyAsStream();
			ObjectMapper mapper = new ObjectMapper();
			@SuppressWarnings("unchecked")
			Map<String, Object> jsonMap = mapper.readValue(in, Map.class);
			recipe.setInstructions((String)jsonMap.get("Instructions"));
			recipe.setTitle((String)jsonMap.get("Title"));
			recipe.setUrl((String)jsonMap.get("WebURL"));
		} catch (IOException e1) {
			logger.error("Cannon process request to Bigoven service, because of " + e1);
		}
	    finally
	    {
	    	get.releaseConnection();	
	    }
		return recipe;
	}
	
	/**
	 * Returns a pseudo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimum value
	 * @param max Maximum value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
}
