package main.validators.allvalidators.strings;

import main.results.ValidationResult;
import main.validators.Validator;

public class MinLength implements Validator {
    private String messageOnFailed;
    private int minLength;

    public MinLength(int minLength, String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
        this.minLength = minLength;
    }

    public MinLength(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    public MinLength() {
    }

    public MinLength of (int minLength) {
        this.minLength = minLength;

        return this;
    }

    public MinLength of (int minLength, String messageOnFailed) {
        this.minLength = minLength;
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
            String elementToCompare = element.toString();

            if(elementToCompare.length() < minLength){
                return ValidationResult.failed(messageOnFailed);
            }else{
                return ValidationResult.success();
            }
        }catch (NumberFormatException | ClassCastException e) {
            return ValidationResult.failed(messageOnFailed);
        }
    }
}
