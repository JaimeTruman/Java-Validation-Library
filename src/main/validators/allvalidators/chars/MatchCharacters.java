package main.validators.allvalidators.chars;

import main.results.ValidationResult;
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
        this.characters = characters;
        this.messageOnFailed = messageOnFailed;

        return this;
    }

    public MatchCharacters of (Collection<? extends Character> collection, String messageOnFailed) {
        this.characters = (Character[]) collection.toArray();
        this.messageOnFailed = messageOnFailed;

        return this;
    }

    public MatchCharacters of(Character[] characters) {
        this.characters = characters;

        return this;
    }

    public MatchCharacters of (Collection<? extends Character> collection) {
        this.characters = (Character[]) collection.toArray();

        return this;
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
