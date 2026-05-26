package il.ac.hit.validation;

import java.util.function.Function;

public interface UserValidation extends Function<User, ValidationResult> {

    default UserValidation and(UserValidation other) {
        return user -> {
            // REMEMBER: "this" also refers to a UserValidation instance.
            ValidationResult result = this.apply(user);
            // If the first check passes, run the second check. Otherwise, return the failure.
            return result.isValid() ? other.apply(user) : result;
        };
    }

    default UserValidation or(UserValidation other) {
        return user -> {
            // REMEMBER: "this" also refers to a UserValidation instance.
            ValidationResult result1 = this.apply(user);
            if (result1.isValid()) return new Valid();

            ValidationResult result2 = other.apply(user);
            if (result2.isValid()) return new Valid();

            return new Invalid("Both OR conditions failed.");
        };
    }

    default UserValidation xor(UserValidation other) {
        return user -> {
            // REMEMBER: "this" also refers to a UserValidation instance.
            boolean isThisValid = this.apply(user).isValid();
            boolean isOtherValid = other.apply(user).isValid();
            if (isThisValid ^ isOtherValid) {
                return new Valid();
            }
            return new Invalid("XOR condition failed: Either both were valid, or both were invalid.");
        };
    }

    static UserValidation all(UserValidation... validations) {
        return user -> {
            for (UserValidation validation : validations) {
                ValidationResult result = validation.apply(user);
                // Fail immediately on the first invalid result
                if (!result.isValid()) {
                    return result;
                }
            }
            return new Valid();
        };
    }

    static UserValidation none(UserValidation... validations) {
        return user -> {
            for (UserValidation validation : validations) {
                ValidationResult result = validation.apply(user);
                // If ANY of them are valid, the "none" condition fails
                if (result.isValid()) {
                    return new Invalid("A condition was unexpectedly fulfilled in a 'none' check.");
                }
            }
            return new Valid();
        };
    }

    static UserValidation emailEndsWithIL() {
        return user -> user.getEmail() != null && user.getEmail().endsWith("il")
                ? new Valid()
                : new Invalid("Email does not end with 'il'.");
    }

    static UserValidation emailLengthBiggerThan10() {
        return user -> user.getEmail() != null && user.getEmail().length() > 10
                ? new Valid()
                : new Invalid("Email length is not greater than 10.");
    }

    static UserValidation passwordLengthBiggerThan8() {
        return user -> user.getPassword() != null && user.getPassword().length() > 8
                ? new Valid()
                : new Invalid("Password length is not greater than 8.");
    }

    static UserValidation passwordIncludesLettersNumbersOnly() {
        return user -> {
            // Using Regex to check for only letters (upper/lowercase) and numbers
            if (user.getPassword() != null && user.getPassword().matches("^[a-zA-Z0-9]+$")) {
                return new Valid();
            }
            return new Invalid("Password must contain only letters and numbers.");
        };
    }

    static UserValidation passwordIncludesDollarSign() {
        return user -> user.getPassword() != null && user.getPassword().contains("$")
                ? new Valid()
                : new Invalid("Password does not include a '$' sign.");
    }

    static UserValidation passwordIsDifferentFromUsername() {
        return user -> user.getPassword() != null && !user.getPassword().equals(user.getUsername())
                ? new Valid()
                : new Invalid("Password must be different from the username.");
    }

    static UserValidation ageBiggerThan18() {
        return user -> user.getAge() > 18
                ? new Valid()
                : new Invalid("Age is not greater than 18.");
    }

    static UserValidation usernameLengthBiggerThan8() {
        return user -> user.getUsername() != null && user.getUsername().length() > 8
                ? new Valid()
                : new Invalid("Username length is not greater than 8.");
    }
}