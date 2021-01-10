package main.validators.strings;

import main.ValidationResult;
import main.validators.Validator;

import java.util.Collection;

public class NotIncludeCharacters implements Validator {
    private String messageOnFailed;
    private Character[] characters;

    public NotIncludeCharacters(Character[] characters, String messageOnFailed) {
        this.characters = characters;
        this.messageOnFailed = messageOnFailed;
    }

    public NotIncludeCharacters(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    public NotIncludeCharacters() { }

    public NotIncludeCharacters of (Character... characters) {
        return new NotIncludeCharacters(characters, messageOnFailed);
    }

    public NotIncludeCharacters of (Collection<? extends Character> characters) {
        return new NotIncludeCharacters((Character[]) characters.toArray(), messageOnFailed);
    }

    public NotIncludeCharacters of (String messageOnFailed, Character... characters) {
        return new NotIncludeCharacters(characters, messageOnFailed);
    }

    public NotIncludeCharacters of (Collection<? extends Character> characters, String messageOnFailed) {
        return new NotIncludeCharacters((Character[]) characters.toArray(), messageOnFailed);
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
