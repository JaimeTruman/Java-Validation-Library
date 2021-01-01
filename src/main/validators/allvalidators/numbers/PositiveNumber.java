package main.validators.allvalidators.numbers;

import main.results.ValidationResult;
import main.validators.Validator;

public class PositiveNumber implements Validator {
    private final String messageOnFailed;

    public PositiveNumber(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }


    @Override
    public ValidationResult check(Object element) {
        try{
            int number = Integer.parseInt(element.toString());

            if(number >= 0)
                return ValidationResult.success();
            else
                return ValidationResult.failed(messageOnFailed);

        }catch (NumberFormatException | ClassCastException e) {
            return ValidationResult.failed(messageOnFailed);
        }
    }
}
