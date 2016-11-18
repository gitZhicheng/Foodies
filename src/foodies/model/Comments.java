package foodies.model;

import java.util.Date;

public class Comments {
	protected int commentId;
	protected String contend;
	protected Date created;
	protected Users user;
	
	public Comments(int commentId, String contend, Date created, Users user) {
		super();
		this.commentId = commentId;
		this.contend = contend;
		this.created = created;
		this.user = user;
	}

	public Comments(int commentId) {
		this.commentId = commentId;
	}

	public Comments(String contend, Date created, Users user) {
		super();
		this.contend = contend;
		this.created = created;
		this.user = user;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
}
