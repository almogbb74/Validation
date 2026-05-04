package il.ac.hit.validation;

public class PlatinumUser extends User {
    public PlatinumUser(String username, String email, String password, int age) {
        super(username, email, password, age);
    }

    @Override
    public void greeting() {
        System.out.println("Welcome, Platinum User " + getUsername() + "!\n");
    }
}