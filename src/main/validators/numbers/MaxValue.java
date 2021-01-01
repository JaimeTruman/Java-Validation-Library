package main.validators.numbers;

import main.ValidationResult;
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
        return new MaxValue(maxValue, messageOnFailed);
    }

    public MaxValue of (int maxValue, String messageOnFailed) {
        return new MaxValue(maxValue, messageOnFailed);
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
