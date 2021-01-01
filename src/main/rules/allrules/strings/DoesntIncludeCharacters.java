package main.rules.allrules.strings;

import main.results.Result;
import main.rules.ManyParams;

public class DoesntIncludeCharacters implements ManyParams {
    private final Character[] characters;
    private final String messageOnFailed;

    public DoesntIncludeCharacters(Character[] characters, String messageOnFailed) {
        this.characters = characters;
        this.messageOnFailed = messageOnFailed;
    }

    @Override
    public Character[] getParams() {
        return characters;
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public Result check(Object object) {
        try{
            String word = String.valueOf(object);

            for(int i = 0; i < word.length(); i++){
                char charOfWordToCheck = word.charAt(i);

                for (Character character : characters) {
                    if (charOfWordToCheck == character) {
                        return Result.failed(messageOnFailed);
                    }
                }
            }

            return Result.success();
        }catch (ClassCastException e) {
            return Result.failed(messageOnFailed);
        }
    }
}
