package main.validators.allvalidators.strings;

import main.results.ValidationResult;
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
        this.characters = characters;

        return this;
    }

    public IncludeCharacters of (Collection<? extends Character> characters) {
        this.characters = (Character[]) characters.toArray();

        return this;
    }

    public IncludeCharacters of (Character[] characters, String messageOnFailed) {
        this.characters = characters;
        this.messageOnFailed = messageOnFailed;

        return this;
    }

    public IncludeCharacters of (Collection<? extends Character> characters, String messageOnFailed) {
        this.characters = (Character[]) characters.toArray();
        this.messageOnFailed = messageOnFailed;

        return this;
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
        }catch (ClassCastException e) {
            return ValidationResult.failed(messageOnFailed);
        }
    }
}
