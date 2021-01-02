package main.validators.strings;

import main.ValidationResult;
import main.validators.Validator;

import java.util.Collection;

public class IncludeCharacters implements Validator {
    private String messageOnFailed;
    private Character[] characters;

    public IncludeCharacters(Character[] characters, String messageOnFailed) {
        this.characters = characters;
        this.messageOnFailed = messageOnFailed;
    }

    public IncludeCharacters(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    public IncludeCharacters() { }

    public IncludeCharacters of (Character[] characters) {
        return new IncludeCharacters(characters, messageOnFailed);
    }

    public IncludeCharacters of (Collection<? extends Character> characters) {
        return new IncludeCharacters((Character[]) characters.toArray(), messageOnFailed);
    }

    public IncludeCharacters of (Character[] characters, String messageOnFailed) {
        return new IncludeCharacters(characters, messageOnFailed);
    }

    public IncludeCharacters of (Collection<? extends Character> characters, String messageOnFailed) {
        return new IncludeCharacters((Character[]) characters.toArray(), messageOnFailed);
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public ValidationResult check(Object object) {
        try{
            String word = String.valueOf(object);

            for(int i = 0; i < word.length(); i++){
                char charOfWordToCheck = word.charAt(i);

                for (Character character : characters) {
                    if (charOfWordToCheck == character) {
                        return ValidationResult.failed(messageOnFailed);
                    }
                }
            }

            return ValidationResult.success();
        }catch (Exception e) {
            return ValidationResult.failed(messageOnFailed);
        }
    }
}
