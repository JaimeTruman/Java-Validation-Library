package main.results;

public final class Success extends Result{
    protected Success () {}

    @Override
    public boolean isSuccessful() {
        return true;
    }
}
