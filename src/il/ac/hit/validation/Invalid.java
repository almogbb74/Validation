package il.ac.hit.validation;

import java.util.Optional;

public class Invalid implements IValidationResult {
    private final String reason;

    // Constructor to capture the error message
    public Invalid(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean isValid() {
        return false; // Always false for an Invalid object
    }

    @Override
    public Optional<String> getReason() {
        // Return the reason wrapped in an Optional
        return Optional.of(reason);
    }
}