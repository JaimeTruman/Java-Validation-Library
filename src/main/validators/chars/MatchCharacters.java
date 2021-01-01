package main.validators.chars;

import main.ValidationResult;
import main.validators.Validator;

import java.util.Collection;


public class MatchCharacters implements Validator {
    private String messageOnFailed;
    private Character[] characters;

    public MatchCharacters(Character[] characters, String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
        this.characters = characters;
    }

    public MatchCharacters(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    public MatchCharacters () {}

    public MatchCharacters of(Character[] characters, String messageOnFailed) {
        return new MatchCharacters(characters, messageOnFailed);
    }

    public MatchCharacters of (Collection<? extends Character> collection, String messageOnFailed) {
        return new MatchCharacters((Character[]) collection.toArray(), messageOnFailed);
    }

    public MatchCharacters of(Character[] characters) {
        return new MatchCharacters(characters, this.messageOnFailed);
    }

    public MatchCharacters of (Collection<? extends Character> collection) {
        return new MatchCharacters((Character[]) collection.toArray(), messageOnFailed);
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public ValidationResult check(Object object) {
        try{
            Character charToCompare = (Character) object;

            for (Character character : characters) {
                if (charToCompare == character) {
                    return ValidationResult.success();
                }
            }

            return ValidationResult.failed(messageOnFailed);
        }catch (ClassCastException e) {
            return ValidationResult.failed(messageOnFailed);
        }
    }
}
