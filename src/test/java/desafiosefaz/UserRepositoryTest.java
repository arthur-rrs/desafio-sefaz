package desafiosefaz;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.arthursilva.desafiosefaz.model.entity.User;
import com.arthursilva.desafiosefaz.repository.UserRepository;
import com.arthursilva.desafiosefaz.repository.factory.UserRepositoryFactory;

import junit.framework.Assert;

public class UserRepositoryTest {

	private UserRepository repository;
	
	private User user = null;
	
	@Before
	public void setUp() throws Exception {
		repository = new UserRepositoryFactory().getUserRepository();
		repository
		.findAll()
		.forEach( user -> repository.removeById( user.getId() ) );
		user = new User();
		user.setEmail("test@faker.com");
		user.setName("test");
		user.setPassword("123456");
	}
		
	@Test
	public void testUserSave() {
		System.out.println("\n\n" + user.getName() + "\n\n");
		repository.save(user);
		Assert.assertNotNull(user.getId());
	}
	
	@Test
	public void testFindAllUsers() 
	{
		repository.save(user);
		List<User> users = repository.findAll();
		Assert.assertTrue(users.size() > 0);
	}
	
	@Test 
	public void testAlterUser() {
		repository.save(user);
		user.setEmail("test@change.com");
		repository.update(user);
		Assert.assertEquals("test@change.com", user.getEmail());
	}
	@Test
	public void testRemoveUser() {
		repository.save(user);
		repository.removeById(user.getId());
		Assert.assertNull(repository.get(user.getId()));
	}
}
