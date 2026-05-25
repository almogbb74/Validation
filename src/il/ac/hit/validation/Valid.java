package il.ac.hit.validation;

import java.util.Optional;

public class Valid implements ValidationResult {

    @Override
    public boolean isValid() {
        return true; // Always true for a Valid object
    }

    @Override
    public Optional<String> getReason() {
        // Since it is valid, there is no error reason to provide.
        return Optional.empty();
    }
}