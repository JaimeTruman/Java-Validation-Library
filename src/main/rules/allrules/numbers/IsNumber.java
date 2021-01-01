package main.rules.allrules.numbers;

import main.results.Result;
import main.rules.NoParam;

public class IsNumber implements NoParam {
    private final String messageOnFailed;

    public IsNumber(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public Result check(Object element) {
        try{
            Integer integerCastTest = Integer.parseInt(element.toString());

            return Result.success();
        }catch (NumberFormatException | ClassCastException e) {
            return Result.failed(messageOnFailed);
        }
    }
}
