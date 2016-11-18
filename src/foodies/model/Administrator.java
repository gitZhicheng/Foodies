package foodies.model;

public class Administrator extends Users{

	public Administrator(int userId, String userName, String password, String firstName, String lastName,
			String email) {
		super(userId, userName, password, firstName, lastName, email);
	}

	public Administrator(String userName, String password, String firstName, String lastName,
			String email) {
		super(userName, password, firstName, lastName, email);
	}

	public Administrator(int userId) {
		super(userId);
	}


}
