package main.validators.strings;

import main.ValidationResult;
import main.validators.Validator;

public class NotEqualsIgnoreCase implements Validator {
    private String word;
    private final String messageOnFailed;

    public NotEqualsIgnoreCase(String word, String messageOnFailed) {
        this.word = word;
        this.messageOnFailed = messageOnFailed;
    }

    public NotEqualsIgnoreCase(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    public NotEqualsIgnoreCase of (String word){
        return new NotEqualsIgnoreCase(word, messageOnFailed);
    }

    public NotEqualsIgnoreCase of (String word, String messageOnFailed) {
        return new NotEqualsIgnoreCase(word, messageOnFailed);
    }

    public String getWord() {
        return word;
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public ValidationResult check(Object object) {
        try{;
            String wordToCheck = (String) object;

            return wordToCheck.equalsIgnoreCase(word) ? ValidationResult.failed(messageOnFailed) : ValidationResult.success();
        }catch (Exception e) {
            return ValidationResult.failed(messageOnFailed);
        }
    }
}
