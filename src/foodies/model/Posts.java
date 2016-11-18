package foodies.model;

import java.util.Date;

public class Posts {
	protected int postId;
	protected String title;
	protected String content;
	protected String image;
	protected Users user;
	protected Date created;
	protected Recipes recipe;

	public Posts(int postId, String title, String content, String image, Users user, Date created, Recipes recipe) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.image = image;
		this.user = user;
		this.created = created;
		this.recipe = recipe;
	}

	public Posts(int postId) {
		this.postId = postId;
	}

	public Posts(String title, String content, String image, Users user, Date created, Recipes recipe) {
		super();
		this.title = title;
		this.content = content;
		this.image = image;
		this.user = user;
		this.created = created;
		this.recipe = recipe;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
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
