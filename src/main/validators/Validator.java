package main.validators;


import main.ValidationResult;

public interface Validator {
    String getMessageOnFailed();
    ValidationResult check(Object object);
}
