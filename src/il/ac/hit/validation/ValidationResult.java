package il.ac.hit.validation;

import java.util.Optional;

public interface ValidationResult {
    // Returns true if the validation passed, false otherwise
    boolean isValid();

    // Returns the reason for failure (if any)
    Optional<String> getReason();
}