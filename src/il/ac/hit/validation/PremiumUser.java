package il.ac.hit.validation;

public class PremiumUser extends User {
    public static final String GREETING_TEMPLATE = "Welcome, Premium User %s!\n";

    public PremiumUser(String username, String email, String password, int age) {
        super(username, email, password, age);
    }

    @Override
    public void greeting(){
        System.out.printf(GREETING_TEMPLATE,getUsername());
    }
}