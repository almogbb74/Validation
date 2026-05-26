package il.ac.hit.validation;

import java.util.Optional;

public class Valid implements ValidationResult {

    // Always true for a Valid object
    @Override
    public boolean isValid() {
        return true;
    }

    // Since it is valid, there is no error reason to provide.
    @Override
    public Optional<String> getReason() {
        return Optional.empty();
    }
}