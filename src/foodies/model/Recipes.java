package foodies.model;

import java.util.Date;

public class Recipes {
	protected int recipeId;
	protected String postName;
	protected String description;
	protected String image;
	protected String step;
	protected int cookingTime;
	protected Date created;
	protected CuisineTypes cuisineTypes;
	protected String ingredients;    //the ingredients is a string which is consist of every ingredient's id
	protected Users user;
	
	public Recipes(int recipeId, String postName, String description, String image, String step, int cookingTime,
			Date created, CuisineTypes cuisineTypes, String ingredients, Users user) {
		super();
		this.recipeId = recipeId;
		this.postName = postName;
		this.description = description;
		this.image = image;
		this.step = step;
		this.cookingTime = cookingTime;
		this.created = created;
		this.cuisineTypes = cuisineTypes;
		this.ingredients = ingredients;
		this.user = user;
	}

	public Recipes(int recipeId) {
		this.recipeId = recipeId;
	}

	public Recipes(String postName, String description, String image, String step, int coookingTime, Date created,
			CuisineTypes cuisineTypes, String ingredients, Users user) {
		super();
		this.postName = postName;
		this.description = description;
		this.image = image;
		this.step = step;
		this.cookingTime = coookingTime;
		this.created = created;
		this.cuisineTypes = cuisineTypes;
		this.ingredients = ingredients;
		this.user = user;
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

	public int getCookingTime() {
		return cookingTime;
	}

	public void setCoookingTime(int cookingTime) {
		this.cookingTime = cookingTime;
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

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
