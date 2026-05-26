package il.ac.hit.validation;

public class UserFactory {

    public static final String ERR_USER_TYPE_NULL = "User type cannot be null.";
    public static final String TYPE_BASIC = "basic";
    public static final String TYPE_PREMIUM = "premium";
    public static final String TYPE_PLATINUM = "platinum";
    public static final String ERR_INVALID_USER_TYPE = "Invalid user type. Must be 'basic', 'premium', or 'platinum'.";

    public static User createUser(String type, String username, String email, String password, int age) {
        if (type == null) {
            throw new IllegalArgumentException(ERR_USER_TYPE_NULL);
        }

        switch (type.toLowerCase()) {
            case TYPE_BASIC:
                return new BasicUser(username, email, password, age);
            case TYPE_PREMIUM:
                return new PremiumUser(username, email, password, age);
            case TYPE_PLATINUM:
                return new PlatinumUser(username, email, password, age);
            default:
                throw new IllegalArgumentException(ERR_INVALID_USER_TYPE);
        }
    }
}