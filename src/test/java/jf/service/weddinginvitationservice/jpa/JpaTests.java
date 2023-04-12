package jf.service.weddinginvitationservice.jpa;

import jf.service.weddinginvitationservice.model.Rsvp;
import jf.service.weddinginvitationservice.model.UserWishes;
import jf.service.weddinginvitationservice.model.Users;
import jf.service.weddinginvitationservice.repository.RsvpRepository;
import jf.service.weddinginvitationservice.repository.UserRepository;
import jf.service.weddinginvitationservice.repository.UserWishRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class JpaTests {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserWishRepository userWishRepository;
    @Autowired
    private RsvpRepository rsvpRepository;
    private Users user;

    @BeforeEach
    void setUp() {
        user = new Users();
        user.setId(UUID.randomUUID());
        user.setGender(0);
        user.setMarried(false);
        user.setName("Firza Gustama");
        user.setPhoneNumber("081931066028");

        userRepository.save(user);
    }

    @Test
    public void user_save_success() {
        Optional<Users> userData = userRepository.findById(user.getId());
        Assertions.assertNotNull(userData);

        Users data = userData.get();
        Assertions.assertEquals(data.getId(), user.getId());
        Assertions.assertEquals(data.getName(), user.getName());
        Assertions.assertEquals(data.getGender(), user.getGender());
        Assertions.assertEquals(data.isMarried(), user.isMarried());
        Assertions.assertEquals(data.getPhoneNumber(), user.getPhoneNumber());
    }
    @Test
    public void userWish_save_success() {
        Date dateNow = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:ss");

        UserWishes data = userWishRepository.save(new UserWishes("ini adalah wish", dateNow, user));
        Assertions.assertEquals(data.getWish(), "ini adalah wish");
        Assertions.assertEquals(dateFormat.format(data.getCreatedDate()), dateFormat.format(dateNow));
        Assertions.assertEquals(data.getUser().getName(), "Firza Gustama");
    }

    @Test
    public void rsvp_save_success() {
        Rsvp rsvp = new Rsvp();
        rsvp.setUser(user);
        rsvp.setPax(2);

        Rsvp data = rsvpRepository.save(rsvp);
        Assertions.assertEquals(data.getUser().getId(), user.getId());
        Assertions.assertEquals(data.getPax(), 2);
    }
}
