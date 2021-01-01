package main.validators.numbers;

import main.ValidationResult;
import main.validators.Validator;

public class Number implements Validator {
    private final String messageOnFailed;

    public Number(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public ValidationResult check(Object element) {
        try{
             Double.parseDouble(element.toString());

            return ValidationResult.success();
        }catch (NumberFormatException | ClassCastException e) {
            return ValidationResult.failed(messageOnFailed);
        }
    }
}
