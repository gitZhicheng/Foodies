package foodies.tools;

import java.sql.SQLException;

import foodies.dal.*;
import foodies.model.*;

public class Inserter {

	public static void main(String[] args) throws SQLException {
		//DAO instances
		UsersDao usersDao = UsersDao.getInstance();
		AdministratorDao administratorDao = AdministratorDao.getInstance();
		NoviceDao noviceDao = NoviceDao.getInstance();
		ExperiencedDao experiencedDao = ExperiencedDao.getInstance();
		
		// INSERT
		Users user1 = new Users("danlai1", "abcde", "dan", "lai", "danlai@gmail.com");
		user1 = usersDao.create(user1);
		
		Administrator a1 = new Administrator("danlai2", "abcde", "dan", "lai", "danlai@gmail.com");
		a1 = administratorDao.create(a1);

		Novice n1 = new Novice("danlai3", "abcde", "dan", "lai", "danlai@gmail.com");
		n1 = noviceDao.create(n1);
			
		
		Experienced e1 = new Experienced("danlai4", "abcde", "dan", "lai", "danlai@gmail.com");
		e1 = experiencedDao.create(e1);
		
		
		Users u1 = usersDao.getUserById(1);
		System.out.format("Reading U: i:%d c:%s c:%s r:%s u:%s u:%s\n",
				u1.getUserId(), u1.getUserName(), u1.getFirstName(), u1.getFirstName(),
				u1.getLastName(), u1.getPassword()); 
		
		
		Experienced e2 = experiencedDao.getExperiencedById(4);
		System.out.format("Reading E: i:%d c:%s c:%s r:%s u:%s u:%s\n",
				e2.getUserId(), e2.getUserName(), e2.getFirstName(), e2.getFirstName(),
				e2.getLastName(), e2.getPassword()); 
		
		
		usersDao.delete(user1);
		administratorDao.delete(a1);
		experiencedDao.delete(e2);
		noviceDao.delete(n1);
		
	}	
	
	
	
	
}
