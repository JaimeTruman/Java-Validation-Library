package main.validators;

import main.ValidationResult;

public class NotNull implements Validator {
    private final String messageOnFailed;

    public NotNull(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public ValidationResult check(Object object) {
        return object == null ? ValidationResult.failed(messageOnFailed) : ValidationResult.success();
    }
}
