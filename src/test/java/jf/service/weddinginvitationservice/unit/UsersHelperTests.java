package jf.service.weddinginvitationservice.unit;

import jf.service.weddinginvitationservice.util.UserHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsersHelperTests {

    @Test
    public void getTitle_male_married() {
        String title = UserHelper.getTitle(0, true);
        Assertions.assertEquals(title, "Mr.");
    }
    @Test
    public void getTitle_male_not_married() {
        String title = UserHelper.getTitle(0, false);
        Assertions.assertEquals(title, "Mr.");
    }
    @Test
    public void getTitle_female_married() {
        String title = UserHelper.getTitle(1, true);
        Assertions.assertEquals(title, "Mrs.");
    }
    @Test
    public void getTitle_female_not_married() {
        String title = UserHelper.getTitle(1, false);
        Assertions.assertEquals(title, "Ms.");
    }
}
