package main.validators.numbers;

import main.ValidationResult;
import main.validators.Validator;

public class Same implements Validator {
    private String messageOnFailed;
    private int numberToBeSame;

    public Same(int numberToBeSame, String messageOnFailed) {
        this.numberToBeSame = numberToBeSame;
        this.messageOnFailed = messageOnFailed;
    }

    public Same(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    public Same() { }

    public Same as (int numberToBeSame) {
        return new Same(numberToBeSame, messageOnFailed);
    }

    public Same as (int numberToBeSame, String messageOnFailed) {
        return new Same(numberToBeSame, messageOnFailed);
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public ValidationResult check(Object object) {
        try{
            int numberToCompare = Integer.parseInt(object.toString());

            return numberToCompare == numberToBeSame ? ValidationResult.success() : ValidationResult.failed(messageOnFailed);
        }catch (Exception e) {
            return ValidationResult.failed(messageOnFailed);
        }
    }
}
