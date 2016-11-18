package foodies.model;

import java.util.Date;

public class RecipeComments extends Comments{
	protected Recipes recipe;
	
	public RecipeComments(int commentId, String contend, Date created, Users user, Recipes recipe) {
		super(commentId, contend, created, user);
		this.recipe = recipe;
	}
	
	public RecipeComments(int commentId) {
		super(commentId);
	}

	public Recipes getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipes recipe) {
		this.recipe = recipe;
	}

}
