package food.model;

public class Novice extends Users{

	public Novice(int userId, String userName, String password, String firstName, String lastName, String email) {
		super(userId, userName, password, firstName, lastName, email);
	}
	
	public Novice(int userId) {
		super(userId);
	}

}
