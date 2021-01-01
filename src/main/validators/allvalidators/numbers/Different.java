package main.validators.allvalidators.numbers;

import main.results.ValidationResult;
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
        this.numberToBeDifferent = numberToBeDifferent;

        return this;
    }

    public Different of (int numberToBeDifferent, String messageOnFailed) {
        this.numberToBeDifferent = numberToBeDifferent;
        this.messageOnFailed = messageOnFailed;

        return this;
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
        }catch (NumberFormatException | ClassCastException e) {
            return ValidationResult.failed(messageOnFailed);
        }
    }
}
