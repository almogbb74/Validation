package il.ac.hit.validation;

import java.util.Optional;

public class Invalid implements ValidationResult {
    private final String reason;

    public Invalid(String reason) {
        this.reason = reason;
    }

    // Always false for an Invalid object
    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public Optional<String> getReason() {
        return Optional.of(reason);
    }
}