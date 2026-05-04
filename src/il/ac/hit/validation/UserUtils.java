package il.ac.hit.validation;

import java.util.Comparator;

public class UserUtils {

    /**
     * Sorts an array of User objects using the Template Method pattern.
     * The sorting algorithm is the template, and the specific comparison
     * logic is provided by the Comparator so we could sort the array by age, name,etc.
     *
     * @param users      The array of users to sort
     * @param comparator The comparator that defines the sorting logic (e.g., by age, username, etc.)
     */
    public static void sort(User[] users, Comparator<User> comparator) {
        if (users == null || comparator == null) {
            return; // Safety check
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