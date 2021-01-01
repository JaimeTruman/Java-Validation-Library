package main.results;

public class Failed extends Result{
    private final String message;

    protected Failed(String message) {
        this.message = message;
    }

    @Override
    public boolean isSuccessful() {
        return false;
    }

    public String getMessage() {
        return message;
    }
}
