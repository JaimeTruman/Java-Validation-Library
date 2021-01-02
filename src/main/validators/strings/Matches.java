package main.validators.strings;

import main.ValidationResult;
import main.validators.Validator;

public class Matches implements Validator {
    private String messageOnFailed;
    private String regExp;

    public Matches(String regExp, String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
        this.regExp = regExp;
    }

    public Matches(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    public Matches() { }

    public Matches of (String regExp) {
        return new Matches(regExp, messageOnFailed);
    }

    public Matches of (String regExp, String messageOnFailed) {
        return new Matches(regExp, messageOnFailed);
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public ValidationResult check(Object element) {
        try{
            String elementToMatch = element.toString();

            if(elementToMatch.matches(regExp))
                return ValidationResult.success();
            else
                return ValidationResult.failed(messageOnFailed);

        }catch (Exception e) {
            return ValidationResult.failed(messageOnFailed);
        }
    }
}
