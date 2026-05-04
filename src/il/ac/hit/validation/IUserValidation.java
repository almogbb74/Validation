package il.ac.hit.validation;

import java.util.function.Function;

public interface IUserValidation extends Function<User, IValidationResult> {

    default IUserValidation and(IUserValidation other) {
        return user -> {
            IValidationResult result = this.apply(user); // REMEMBER: "this" also refers to a UserValidation instance.

            // If the first check passes, run the second check. Otherwise, return the failure.
            return result.isValid() ? other.apply(user) : result;
        };
    }

    default IUserValidation or(IUserValidation other) {
        return user -> {
            IValidationResult result1 = this.apply(user); // REMEMBER: "this" also refers to a UserValidation instance.
            if (result1.isValid()) return new Valid();

            IValidationResult result2 = other.apply(user);
            if (result2.isValid()) return new Valid();

            return new Invalid("Both OR conditions failed.");
        };
    }

    default IUserValidation xor(IUserValidation other) {
        return user -> {
            boolean isThisValid = this.apply(user).isValid(); // REMEMBER: "this" also refers to a UserValidation instance.
            boolean isOtherValid = other.apply(user).isValid();

            // ^ is the logical XOR operator in Java
            if (isThisValid ^ isOtherValid) {
                return new Valid();
            }
            return new Invalid("XOR condition failed: Either both were valid, or both were invalid.");
        };
    }

    static IUserValidation all(IUserValidation... validations) {
        return user -> {
            for (IUserValidation validation : validations) {
                IValidationResult result = validation.apply(user);
                // Fail immediately on the first invalid result
                if (!result.isValid()) {
                    return result;
                }
            }
            return new Valid();
        };
    }

    static IUserValidation none(IUserValidation... validations) {
        return user -> {
            for (IUserValidation validation : validations) {
                IValidationResult result = validation.apply(user);
                // If ANY of them are valid, the "none" condition fails
                if (result.isValid()) {
                    return new Invalid("A condition was unexpectedly fulfilled in a 'none' check.");
                }
            }
            return new Valid();
        };
    }

    static IUserValidation emailEndsWithIL() {
        return user -> user.getEmail() != null && user.getEmail().endsWith("il")
                ? new Valid()
                : new Invalid("Email does not end with 'il'.");
    }

    static IUserValidation emailLengthBiggerThan10() {
        return user -> user.getEmail() != null && user.getEmail().length() > 10
                ? new Valid()
                : new Invalid("Email length is not greater than 10.");
    }

    static IUserValidation passwordLengthBiggerThan8() {
        return user -> user.getPassword() != null && user.getPassword().length() > 8
                ? new Valid()
                : new Invalid("Password length is not greater than 8.");
    }

    static IUserValidation passwordIncludesLettersNumbersOnly() {
        return user -> {
            // Using Regex to check for only letters (upper/lowercase) and numbers
            if (user.getPassword() != null && user.getPassword().matches("^[a-zA-Z0-9]+$")) {
                return new Valid();
            }
            return new Invalid("Password must contain only letters and numbers.");
        };
    }

    static IUserValidation passwordIncludesDollarSign() {
        return user -> user.getPassword() != null && user.getPassword().contains("$")
                ? new Valid()
                : new Invalid("Password does not include a '$' sign.");
    }

    static IUserValidation passwordIsDifferentFromUsername() {
        return user -> user.getPassword() != null && !user.getPassword().equals(user.getUsername())
                ? new Valid()
                : new Invalid("Password must be different from the username.");
    }

    static IUserValidation ageBiggerThan18() {
        return user -> user.getAge() > 18
                ? new Valid()
                : new Invalid("Age is not greater than 18.");
    }

    static IUserValidation usernameLengthBiggerThan8() {
        return user -> user.getUsername() != null && user.getUsername().length() > 8
                ? new Valid()
                : new Invalid("Username length is not greater than 8.");
    }
}