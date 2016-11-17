package food.model;

import java.math.BigDecimal;

public class Reviews {
	protected int reviewId;
	protected BigDecimal rating;
	protected Users user;
	protected Recipes recipe;
	
	public Reviews(int reviewId, BigDecimal rating, Users user, Recipes recipe) {
		super();
		this.reviewId = reviewId;
		this.rating = rating;
		this.user = user;
		this.recipe = recipe;
	}
	
	public Reviews(int reviewId) {
		this.reviewId = reviewId;
	}

	public Reviews(BigDecimal rating, Users user, Recipes recipe) {
		super();
		this.rating = rating;
		this.user = user;
		this.recipe = recipe;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Recipes getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipes recipe) {
		this.recipe = recipe;
	}

}
