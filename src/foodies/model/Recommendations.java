package foodies.model;

import java.util.Date;

public class Recommendations {
	protected int recommendationId;
	protected Users from_user;
	protected Users to_user;
	protected String contend;
	protected Date created;
	protected Recipes recipe;

	public Recommendations(int recommendationId, Users from_user, Users to_user, String contend, Date created,
			Recipes recipe) {
		super();
		this.recommendationId = recommendationId;
		this.from_user = from_user;
		this.to_user = to_user;
		this.contend = contend;
		this.created = created;
		this.recipe = recipe;
	}

	public Recommendations(int recommendationId) {
		this.recommendationId = recommendationId;
	}

	public Recommendations(Users from_user, Users to_user, String contend, Date created, Recipes recipe) {
		super();
		this.from_user = from_user;
		this.to_user = to_user;
		this.contend = contend;
		this.created = created;
		this.recipe = recipe;
	}

	public int getRecommendationId() {
		return recommendationId;
	}

	public void setRecommendationId(int recommendationId) {
		this.recommendationId = recommendationId;
	}

	public Users getFrom_user() {
		return from_user;
	}

	public void setFrom_user(Users from_user) {
		this.from_user = from_user;
	}

	public Users getTo_user() {
		return to_user;
	}

	public void setTo_user(Users to_user) {
		this.to_user = to_user;
	}

	public String getContend() {
		return contend;
	}

	public void setContend(String contend) {
		this.contend = contend;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Recipes getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipes recipe) {
		this.recipe = recipe;
	}

}
