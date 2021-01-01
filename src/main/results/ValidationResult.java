package main.results;

public class ValidationResult {
    private final boolean isSuccessful;
    private final String message;

    public ValidationResult(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccessful() {return isSuccessful;};

    public boolean isFailed () {
        return !isSuccessful();
    }

    public static ValidationResult success () {
        return new ValidationResult(true, "Successful");
    }

    public static ValidationResult failed (String message) {
        return new ValidationResult(false, message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
