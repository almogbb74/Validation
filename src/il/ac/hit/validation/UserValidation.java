package il.ac.hit.validation;

import java.util.function.Function;

public interface UserValidation extends Function<User, ValidationResult> {

    String REASON_INVALID_OR = "Both OR conditions failed.";
    String REASON_INVALID_XOR = "XOR condition failed: Either both were valid, or both were invalid.";
    String REASON_INVALID_NONE = "A condition was unexpectedly fulfilled in a 'none' check.";
    String REASON_INVALID_EMAIL_NOT_END_WITH_IL = "Email does not end with 'il'.";
    String REASON_INVALID_EMAIL_LEN_SHORT = "Email length is not greater than 10.";
    String REASON_INVALID_PASSWORD_LEN = "Password length is not greater than 8.";
    String REASON_INVALID_PASSWORD_LEN_SHORT = REASON_INVALID_PASSWORD_LEN;
    String REASON_INVALID_PASSWORD_LETTERS_NUMBERS = "Password must contain only letters and numbers.";
    String REASON_INVALID_PASSWORD_NOT_LETTERS_NUMBERS = REASON_INVALID_PASSWORD_LETTERS_NUMBERS;
    String REASON_INVALID_PASSWORD_DOLLAR_SIGN = "Password does not include a '$' sign.";
    String REASON_INVALID_PASSWORD_NOT_CONTAIN_DOLLAR_SIGN = REASON_INVALID_PASSWORD_DOLLAR_SIGN;
    String REASON_INVALID_PASSWORD_EQUALS_USERNAME = "Password must be different from the username.";
    String REASON_INVALID_AGE_BELOW_18 = "Age is not greater than 18.";
    String REASON_INVALID_USERNAME_LEN_SHORT = "Username length is not greater than 8.";

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

            return new Invalid(REASON_INVALID_OR);
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
            return new Invalid(REASON_INVALID_XOR);
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
                    return new Invalid(REASON_INVALID_NONE);
                }
            }
            return new Valid();
        };
    }

    static UserValidation emailEndsWithIL() {
        return user -> user.getEmail() != null && user.getEmail().endsWith("il")
                ? new Valid()
                : new Invalid(REASON_INVALID_EMAIL_NOT_END_WITH_IL);
    }

    static UserValidation emailLengthBiggerThan10() {
        return user -> user.getEmail() != null && user.getEmail().length() > 10
                ? new Valid()
                : new Invalid(REASON_INVALID_EMAIL_LEN_SHORT);
    }

    static UserValidation passwordLengthBiggerThan8() {
        return user -> user.getPassword() != null && user.getPassword().length() > 8
                ? new Valid()
                : new Invalid(REASON_INVALID_PASSWORD_LEN_SHORT);
    }

    static UserValidation passwordIncludesLettersNumbersOnly() {
        return user -> {
            // Using Regex to check for only letters (upper/lowercase) and numbers
            if (user.getPassword() != null && user.getPassword().matches("^[a-zA-Z0-9]+$")) {
                return new Valid();
            }
            return new Invalid(REASON_INVALID_PASSWORD_NOT_LETTERS_NUMBERS);
        };
    }

    static UserValidation passwordIncludesDollarSign() {
        return user -> user.getPassword() != null && user.getPassword().contains("$")
                ? new Valid()
                : new Invalid(REASON_INVALID_PASSWORD_NOT_CONTAIN_DOLLAR_SIGN);
    }

    static UserValidation passwordIsDifferentFromUsername() {
        return user -> user.getPassword() != null && !user.getPassword().equals(user.getUsername())
                ? new Valid()
                : new Invalid(REASON_INVALID_PASSWORD_EQUALS_USERNAME);
    }

    static UserValidation ageBiggerThan18() {
        return user -> user.getAge() > 18
                ? new Valid()
                : new Invalid(REASON_INVALID_AGE_BELOW_18);
    }

    static UserValidation usernameLengthBiggerThan8() {
        return user -> user.getUsername() != null && user.getUsername().length() > 8
                ? new Valid()
                : new Invalid(REASON_INVALID_USERNAME_LEN_SHORT);
    }
}