package main.validators;


import main.results.ValidationResult;

public interface Validator {
    String getMessageOnFailed();
    ValidationResult check(Object object);
}
