package main.rules.allrules.strings;

import main.results.Result;
import main.rules.OneParam;

public class Matches implements OneParam {
    private final String messageOnFailed;
    private final String regExp;

    public Matches(String regExp, String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
        this.regExp = regExp;
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public String getParam() {
        return regExp;
    }

    @Override
    public Result check(Object element) {
        try{
            String elementToMatch = element.toString();

            if(elementToMatch.matches(regExp))
                return Result.success();
            else
                return Result.failed(messageOnFailed);

        }catch (ClassCastException e) {
            return Result.failed(messageOnFailed);
        }
    }
}
