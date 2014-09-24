package it.fettuccine.alfresco.recipe;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptException;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;


/**
 * Simply returns the recipe of the day using the {@link RecipeService}
 * @author mindthegab
 *
 */
public class RotdWebscript extends AbstractWebScript {

	
	private RecipeService recipeService;

	public void setRecipeService(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@Override
	public void execute(WebScriptRequest request, WebScriptResponse response)
			throws IOException {

		try
    	{
			Recipe rotd = recipeService.getRotd();
	    	// build a json object
	    	JSONObject obj = new JSONObject();
	    	// put some data on it
	    	obj.put("id", rotd.getId());
	    	obj.put("title", rotd.getTitle());
	    	obj.put("instructions", rotd.getInstructions());
	    	obj.put("url", rotd.getUrl());
	    	
	    	// build a JSON string and send it back
	    	String jsonString = obj.toString();
	    	response.getWriter().write(jsonString);
    	}
    	catch(JSONException e)
    	{
    		throw new WebScriptException("Unable to serialize JSON");
    	}
		
	}
}
