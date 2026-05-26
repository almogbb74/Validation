package il.ac.hit.validation;

import java.util.Comparator;

public class UserUtils {

    public static final String ERR_USERS_COMPARATOR_NULL = "Please provide a non-null array of users and a non-null comparator.";

    public static void sort(User[] users, Comparator<User> comparator) {
        // Safety check
        if (users == null || comparator == null) {
            throw new IllegalArgumentException(ERR_USERS_COMPARATOR_NULL);
        }

        // Bubble sort
        int n = users.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                // The actual comparison logic is provided by the comparator.
                if (comparator.compare(users[j], users[j + 1]) > 0) {
                    User temp = users[j];
                    users[j] = users[j + 1];
                    users[j + 1] = temp;
                }
            }
        }
    }
}