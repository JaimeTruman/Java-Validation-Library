package main.results;

public abstract class Result {
    public abstract boolean isSuccessful();

    public boolean isFailed () {
        return !isSuccessful();
    }

    public static Success success () {
        return new Success();
    }

    public static Failed failed (String message) {
        return new Failed(message);
    }

    @Override
    public String toString() {
        if(isSuccessful()){
            return "Successful";
        }else{
            Failed failed = (Failed) this;

            return failed.getMessage();
        }
    }
}
