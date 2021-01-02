package main.validators.numbers;

import main.ValidationResult;
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

        }catch (Exception e) {
            return ValidationResult.failed(messageOnFailed);
        }
    }
}
