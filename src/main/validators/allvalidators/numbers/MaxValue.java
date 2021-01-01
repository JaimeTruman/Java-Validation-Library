package main.validators.allvalidators.numbers;

import main.results.ValidationResult;
import main.validators.Validator;

public class MaxValue implements Validator {
    private String messageOnFailed;
    private int maxValue;

    public MaxValue(int maxValue, String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
        this.maxValue = maxValue;
    }

    public MaxValue(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    public MaxValue() { }

    public MaxValue of (int maxValue) {
        this.maxValue = maxValue;

        return this;
    }

    public MaxValue of (int maxValue, String messageOnFailed) {
        this.maxValue = maxValue;
        this.messageOnFailed = messageOnFailed;

        return this;
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public ValidationResult check(Object element) {
        try{
            double number = Double.parseDouble(element.toString());

            if(number > maxValue){
                return ValidationResult.failed(messageOnFailed);
            }else{
                return ValidationResult.success();
            }
        }catch (NumberFormatException | ClassCastException e) {
            return ValidationResult.failed(messageOnFailed);
        }
    }
}
