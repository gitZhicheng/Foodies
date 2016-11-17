package foodies.model;

import java.util.Date;

public class Recipes {
	protected int recipeId;
	protected String postName;
	protected String description;
	protected String image;
	protected String step;
	protected int coookingTime;
	protected Date created;
	protected CuisineTypes cuisineTypes;
	protected String ingredientid;    //the ingredientid is a string which is consist of every ingredient's id
	protected Experienced experienced;
	
	public Recipes(int recipeId, String postName, String description, String image, String step, int coookingTime,
			Date created, CuisineTypes cuisineTypes, String ingredientid, Experienced experienced) {
		super();
		this.recipeId = recipeId;
		this.postName = postName;
		this.description = description;
		this.image = image;
		this.step = step;
		this.coookingTime = coookingTime;
		this.created = created;
		this.cuisineTypes = cuisineTypes;
		this.ingredientid = ingredientid;
		this.experienced = experienced;
	}


	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public int getCoookingTime() {
		return coookingTime;
	}

	public void setCoookingTime(int coookingTime) {
		this.coookingTime = coookingTime;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public CuisineTypes getCuisineTypes() {
		return cuisineTypes;
	}

	public void setCuisineTypes(CuisineTypes cuisineTypes) {
		this.cuisineTypes = cuisineTypes;
	}

	public String getIngredientid() {
		return ingredientid;
	}

	public void setIngredientid(String ingredientid) {
		this.ingredientid = ingredientid;
	}

	public Experienced getUser() {
		return experiencedUser;
	}

	public void setExperienced(Experienced experienced) {
		this.experiencedUser = experienced;
	}

}
