package jf.service.weddinginvitationservice;

import jf.service.weddinginvitationservice.unit.UserWishApiTests;
import jf.service.weddinginvitationservice.unit.UsersApiTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class WeddingInvitationServiceApplicationTests {
	@Autowired
	private UsersApiTests usersApiTests;
	@Autowired
	private UserWishApiTests userWishApiTests;
	@Test
	void contextLoads() throws Exception {
		usersApiTests.getUserById_found();
		userWishApiTests.getUserWishes_success();
	}

}
