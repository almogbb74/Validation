package il.ac.hit.validation;

public class PlatinumUser extends User {
    public static final String GREETING_TEMPLATE = "Welcome, Platinum User %s!\n";

    public PlatinumUser(String username, String email, String password, int age) {
        super(username, email, password, age);
    }

    @Override
    public void greeting() {
        System.out.printf(GREETING_TEMPLATE,getUsername());

    }
}