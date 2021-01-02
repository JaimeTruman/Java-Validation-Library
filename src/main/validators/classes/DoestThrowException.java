package main.validators.classes;

import main.ValidationResult;
import main.validators.Validator;

import java.util.function.Supplier;


public class DoestThrowException implements Validator {
    private final String messageOnFailed;

    public DoestThrowException(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public ValidationResult check(Object object) {
        try{
            Supplier<?> supplier = (Supplier<?>) object;
            supplier.get();

            return ValidationResult.success();
        }catch (Exception e) {
            return ValidationResult.failed(messageOnFailed);
        }
    }

    public static DoestThrowException of (String messageOnFailed) {
        return new DoestThrowException(messageOnFailed);
    }
}
