package main.validators.allvalidators;

import main.results.ValidationResult;
import main.validators.Validator;

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
