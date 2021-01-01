package main.validators.allvalidators.numbers;

import main.results.ValidationResult;
import main.validators.Validator;

public class MinValue implements Validator {
    private String messageOnFailed;
    private int minValue;

    public MinValue(int minValue, String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
        this.minValue = minValue;
    }

    public MinValue(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    public MinValue() {
    }

    public MinValue of (int minValue, String messageOnFailed) {
        this.minValue = minValue;
        this.messageOnFailed = messageOnFailed;

        return this;
    }

    public MinValue of (int minValue) {
        this.minValue = minValue;

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

            if(number < minValue){
                return ValidationResult.failed(messageOnFailed);
            }else{
                return ValidationResult.success();
            }
        }catch (NumberFormatException | ClassCastException e) {
            return ValidationResult.failed(messageOnFailed);
        }
    }
}
