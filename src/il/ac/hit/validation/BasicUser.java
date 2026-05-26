package il.ac.hit.validation;

public class BasicUser extends User {
    public static final String GREETING_TEMPLATE = "Welcome, Basic User %s!\n";

    public BasicUser(String username, String email, String password, int age) {
        super(username, email, password, age);
    }

    @Override
    public void greeting() {
        System.out.printf(GREETING_TEMPLATE,getUsername());
    }
}
