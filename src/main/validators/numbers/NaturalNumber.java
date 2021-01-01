package main.validators.numbers;

import main.ValidationResult;
import main.validators.Validator;

public class NaturalNumber implements Validator {
    private String messageOnFailed;

    public NaturalNumber(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }


    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public ValidationResult check(Object element) {
        try{
            double number = Double.parseDouble(element.toString());

            boolean biggerThanZero = number >= 0;
            boolean notDecimal = number % 1 == 0;

            if(biggerThanZero && notDecimal)
                return ValidationResult.success();
            else
                return ValidationResult.failed(messageOnFailed);

        }catch (NumberFormatException | ClassCastException e) {
            return ValidationResult.failed(messageOnFailed);
        }
    }
}
