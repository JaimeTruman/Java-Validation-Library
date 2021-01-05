package main.validators.strings;

import main.ValidationResult;
import main.validators.Validator;

public class EqualsIgnoreCase implements Validator {
    private String word;
    private final String messageOnFailed;

    public EqualsIgnoreCase(String word, String messageOnFailed) {
        this.word = word;
        this.messageOnFailed = messageOnFailed;
    }

    public EqualsIgnoreCase(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    public EqualsIgnoreCase of (String word){
        return new EqualsIgnoreCase(word, messageOnFailed);
    }

    public EqualsIgnoreCase of (String word, String messageOnFailed) {
        return new EqualsIgnoreCase(word, messageOnFailed);
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public ValidationResult check(Object object) {
        try{
            String wordToCheck = (String) object;

            return wordToCheck.equalsIgnoreCase(word) ? ValidationResult.success() : ValidationResult.failed(messageOnFailed);
        }catch (Exception e) {
            return ValidationResult.failed(messageOnFailed);
        }
    }
}
