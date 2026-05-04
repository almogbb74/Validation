package il.ac.hit.validation;

public class User {
    private final String username;
    private final String email;
    private final String password;
    private int age; // Logically, age shouldn't be final since it can change.

    public User(String username, String email, String password, int age) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    // Getters are necessary so the validation methods can access these fields later

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) { // Setter for age to allow modification if needed
        this.age = age;
    }

    public void greeting() {
        System.out.println("Hello, " + username + "!\n");
    }
}