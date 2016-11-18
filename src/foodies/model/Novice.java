package foodies.model;

public class Novice extends Users{

	public Novice(int userId, String userName, String password, String firstName, String lastName, String email) {
		super(userId, userName, password, firstName, lastName, email);
	}

	public Novice(String userName, String password, String firstName, String lastName, String email) {
		super(userName, password, firstName, lastName, email);
	}

	public Novice(int userId) {
		super(userId);
	}

}
