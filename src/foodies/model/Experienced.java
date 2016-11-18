package foodies.model;

public class Experienced extends Users{

	public Experienced(int userId, String userName, String password, String firstName, String lastName, String email) {
		super(userId, userName, password, firstName, lastName, email);
	}

	public Experienced(String userName, String password, String firstName, String lastName, String email) {
		super(userName, password, firstName, lastName, email);
	}

	public Experienced(int userId) {
		super(userId);
	}

}
