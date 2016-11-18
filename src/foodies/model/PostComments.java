package foodies.model;

import java.util.Date;

public class PostComments extends Comments{
	protected Posts post;

	public PostComments(int commentId, String contend, Date created, Users user, Posts post) {
		super(commentId, contend, created, user);
		this.post = post;
	}

	public PostComments(int commentId) {
		super(commentId);
	}

	public Posts getPost() {
		return post;
	}

	public void setPost(Posts post) {
		this.post = post;
	}

}
