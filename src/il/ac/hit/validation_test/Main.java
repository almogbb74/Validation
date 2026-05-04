package il.ac.hit.validation_test;

import il.ac.hit.validation.*;

public class Main {
    static void main() {
        System.out.println("STARTING VALIDATION LIBRARY TESTS...\n");


        // ==========================================
        //  TEST THE FACTORY PATTERN
        // ==========================================

        System.out.println("--- Testing UserFactory ---");

        User validUser = UserFactory.createUser("platinum", "Almog12345", "almog@hit.ac.il", "Password123$", 25);

        User invalidUser = UserFactory.createUser("basic", "bob", "bob@gmail.com", "bob", 15);

        User alphanumericUser = UserFactory.createUser("premium", "Charlie99", "charlie@test.il", "SafePass99", 30);

        System.out.println("Created Users: " +
                validUser.getUsername() + ", " +
                invalidUser.getUsername() + ", " +
                alphanumericUser.getUsername() + "\n");

        validUser.greeting();
        invalidUser.greeting();
        alphanumericUser.greeting();

        // ==========================================
        //  TEST  AND, OR, XOR METHODS
        // ==========================================

        System.out.println("--- Testing Combinators ---");

        // AND Test: Must be over 18 AND email ends with 'il'
        IUserValidation andCheck = IUserValidation.ageBiggerThan18().and(IUserValidation.emailEndsWithIL());

        IValidationResult andResult1 = andCheck.apply(validUser);
        System.out.println("AND Check on Almog (Should be Valid): " + andResult1.isValid());

        IValidationResult andResult2 = andCheck.apply(invalidUser);
        System.out.println("AND Check on Bob (Should be Invalid): " + andResult2.isValid() + " -> Reason: " + andResult2.getReason().orElse(""));

        // OR Test: Username length > 8 OR Age > 18
        IUserValidation orCheck = IUserValidation.usernameLengthBiggerThan8()
                .or(IUserValidation.ageBiggerThan18());
        System.out.println("OR Check on Bob (Fails both, Should be Invalid): " + orCheck.apply(invalidUser).isValid());

        // XOR Test: A fun test! A password cannot contain ONLY letters/numbers XOR contain a '$'.
        // They are mutually exclusive, so XOR should pass if a user has one but not the other!
        IUserValidation xorCheck = IUserValidation.passwordIncludesLettersNumbersOnly()
                .xor(IUserValidation.passwordIncludesDollarSign());

        System.out.println("XOR Check on Almog (Has $, no pure alphanumeric -> Valid): " + xorCheck.apply(validUser).isValid());
        System.out.println("XOR Check on Charlie (Has pure alphanumeric, no $ -> Valid): " + xorCheck.apply(alphanumericUser).isValid());


        // ==========================================
        //   TEST STATIC ALL, NONE METHODS
        // ==========================================

        System.out.println("\n--- Testing Aggregates ---");

        IUserValidation superStrictCheck = IUserValidation.all(
                IUserValidation.ageBiggerThan18(),
                IUserValidation.emailEndsWithIL(),
                IUserValidation.emailLengthBiggerThan10(),
                IUserValidation.passwordLengthBiggerThan8(),
                IUserValidation.passwordIsDifferentFromUsername()
        );
        System.out.println("ALL Check on Almog (Passes all 5 -> Valid): " + superStrictCheck.apply(validUser).isValid());

        IUserValidation noneCheck = IUserValidation.none(
                IUserValidation.ageBiggerThan18(),
                IUserValidation.emailEndsWithIL()
        );
        // Bob is 15 and has a gmail.com address. He passes NO rules, which means the "none" check is Valid!
        System.out.println("NONE Check on Bob (Fails everything -> Valid!): " + noneCheck.apply(invalidUser).isValid());


        // ==========================================
        //   TEST THE SORTING METHOD (BY AGE)
        // ==========================================

        System.out.println("\n--- Testing Template Method (UserUtils.sort) ---");

        User[] usersArray = {alphanumericUser, invalidUser, validUser}; // Ages: 30, 15, 25

        System.out.print("Before Sorting by Age: ");
        for (User u : usersArray) System.out.print(u.getAge() + " ");
        System.out.println();

        // We pass the array and a custom Comparator
        UserUtils.sort(usersArray, (u1, u2) -> Integer.compare(u1.getAge(), u2.getAge()));

        System.out.print("After Sorting by Age:  ");
        for (User u : usersArray) System.out.print(u.getAge() + " ");
        System.out.println("\n\nALL TESTS ARE COMPLETED!");
    }
}