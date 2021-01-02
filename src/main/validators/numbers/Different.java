package main.validators.numbers;

import main.ValidationResult;
import main.validators.Validator;

public class Different implements Validator {
    private String messageOnFailed;
    private int numberToBeDifferent;

    public Different(int numberToBeDifferent, String messageOnFailed) {
        this.numberToBeDifferent = numberToBeDifferent;
        this.messageOnFailed = messageOnFailed;
    }

    public Different(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    public Different() { }

    public Different of (int numberToBeDifferent) {
        return new Different(numberToBeDifferent, messageOnFailed);
    }

    public Different of (int numberToBeDifferent, String messageOnFailed) {
        return new Different(numberToBeDifferent, messageOnFailed);
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public ValidationResult check(Object object) {
        try{
            int  numberToCompare = Integer.parseInt(object.toString());

            return numberToCompare != numberToBeDifferent ? ValidationResult.success() : ValidationResult.failed(messageOnFailed);
        }catch (Exception e) {
            return ValidationResult.failed(messageOnFailed);
        }
    }
}
