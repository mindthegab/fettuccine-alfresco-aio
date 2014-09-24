package it.fettuccine.alfresco.recipe;

import java.util.List;

/**
 * A basic service to retrieve Recipes information
 * @author gabrielecolumbro
 *
 */
public interface RecipeService {

	public List<Recipe> search(String keywords);
	/**
	 * Returns the recipe of the day
	 * @return
	 */
	public Recipe getRotd();
}
