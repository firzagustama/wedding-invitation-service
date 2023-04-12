package jf.service.weddinginvitationservice.util;

public class UserHelper {
    /**
     *
     * @param gender - 0 male / 1 female
     * @param married
     * @return
     */
    public static String getTitle(int gender, boolean married) {
        if (gender == 0) {
            return "Mr.";
        } else if (married) {
            return "Mrs.";
        } else {
            return "Ms.";
        }
    }
}
