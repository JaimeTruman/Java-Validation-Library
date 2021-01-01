package main.validators.classes;

import main.ValidationResult;
import main.validators.Validator;

public class InstanceOf implements Validator {
    private Class<?> classToCheck;
    private String messageOnFailed;

    public InstanceOf(Class<?> classToCheck, String messageOnFailed) {
        this.classToCheck = classToCheck;
        this.messageOnFailed = messageOnFailed;
    }

    public InstanceOf(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    public InstanceOf() {
    }

    public InstanceOf of (Class<?> classToCheck) {
        return new InstanceOf(classToCheck, messageOnFailed);
    }

    public InstanceOf of (Class<?> classToCheck, String messageOnFailed) {
        return new InstanceOf(classToCheck, messageOnFailed);
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public ValidationResult check(Object object) {
        try {
            return classToCheck.isInstance(object) ? ValidationResult.success() : ValidationResult.failed(messageOnFailed);

        }catch (ClassCastException e) {
            return ValidationResult.failed(messageOnFailed);
        }
    }
}
