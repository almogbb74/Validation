package il.ac.hit.validation;

public class PremiumUser extends User {
    public PremiumUser(String username, String email, String password, int age) {
        super(username, email, password, age);
    }

    @Override
    public void greeting(){
        System.out.println("Welcome, Premium User " + getUsername() + "!\n");
    }
}