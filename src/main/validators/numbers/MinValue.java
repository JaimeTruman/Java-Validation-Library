package main.validators.numbers;

import main.ValidationResult;
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
        return new MinValue(minValue, messageOnFailed);
    }

    public MinValue of (int minValue) {
        return new MinValue(minValue, messageOnFailed);
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
