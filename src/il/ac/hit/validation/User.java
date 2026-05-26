package il.ac.hit.validation;

public class User {
    public static final String ERR_USERNAME_NULL = "Username cannot be null or empty.";
    public static final String ERR_EMAIL_NULL = "Email cannot be null or empty.";
    public static final String ERR_PASSWORD_NULL = "Password cannot be null or empty.";
    public static final String ERR_AGE_NEG = "Age cannot be negative.";
    public static final String GREETING_TEMPLATE = "Hello, %s!\n";

    private String username;
    private String email;
    private String password;
    private int age; // Logically, age shouldn't be final since it can change.

    public User(String username, String email, String password, int age) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setAge(age);
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

    public void setUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException(ERR_USERNAME_NULL);
        }
        this.username = username;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException(ERR_EMAIL_NULL);
        }
        this.email = email;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException(ERR_PASSWORD_NULL);
        }
        this.password = password;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException(ERR_AGE_NEG);
        }
        this.age = age;
    }


    public void greeting() {
        System.out.printf(GREETING_TEMPLATE, getUsername());
    }
}