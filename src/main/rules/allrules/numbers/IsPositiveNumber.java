package main.rules.allrules.numbers;

import main.results.Failed;
import main.results.Result;
import main.rules.NoParam;

public class IsPositiveNumber implements NoParam {
    private final String messageOnFailed;

    public IsPositiveNumber(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }


    @Override
    public Result check(Object element) {
        try{
            int number = Integer.parseInt(element.toString());

            if(number >= 0)
                return Result.success();
            else
                return Result.failed(messageOnFailed);

        }catch (NumberFormatException | ClassCastException e) {
            return Failed.failed(messageOnFailed);
        }
    }
}
