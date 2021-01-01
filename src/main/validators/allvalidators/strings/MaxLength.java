package main.validators.allvalidators.strings;

import main.results.ValidationResult;
import main.validators.Validator;

public class MaxLength implements Validator {
    private String messageOnFailed;
    private int maxLength;

    public MaxLength(int maxLength, String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
        this.maxLength = maxLength;
    }

    public MaxLength(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    public MaxLength() {
    }

    public MaxLength of (int maxLength) {
        this.maxLength = maxLength;

        return this;
    }

    public MaxLength of (int maxLength, String messageOnFailed) {
        this.maxLength = maxLength;
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

            if(elementToCompare.length() > maxLength){
                return ValidationResult.failed(messageOnFailed);
            }else{
                return ValidationResult.success();
            }
        }catch (NumberFormatException | ClassCastException e) {
            return ValidationResult.failed(messageOnFailed);
        }
    }
}
