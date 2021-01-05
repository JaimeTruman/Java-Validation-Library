package main.validators;

import main.ValidationResult;

public class NotNull implements Validator {
    private String messageOnFailed;

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

    public NotNull message (String messageOnFailed) {
        return new NotNull(messageOnFailed);
    }
}
