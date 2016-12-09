package foodies.model;

import java.math.BigDecimal;
import java.util.Date;

public class Reviews {
	protected int reviewId;
	protected BigDecimal rating;
	protected Date created;
	protected Users user;
	protected Recipes recipe;

	public Reviews(int reviewId, BigDecimal rating, Date created, Users user, Recipes recipe) {
		super();
		this.reviewId = reviewId;
		this.rating = rating;
		this.created = created;
		this.user = user;
		this.recipe = recipe;
	}

	public Reviews(int reviewId) {
		this.reviewId = reviewId;
	}

	public Reviews(BigDecimal rating, Date created, Users user, Recipes recipe) {
		super();
		this.rating = rating;
		this.created = created;
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
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
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
