package it.fettuccine.alfresco.recipe;

/**
 * Simple data object containing key recipe information
 * @author mindthegab
 *
 */
public class Recipe {

	public Recipe(int id){
		this.id = id;		
	}
	
	private int id;
	
	private String title;
	
	private String instructions;
	
	private String url;

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setId(int id) {
		this.id = id;
	}

}
