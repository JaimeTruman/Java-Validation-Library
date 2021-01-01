package main.rules.allrules.numbers;

import main.results.Result;
import main.rules.OneParam;

public class Different implements OneParam {
    private final int numberToBeDifferent;
    private final String messageOnFailed;

    public Different(int numberToBeDifferent, String messageOnFailed) {
        this.numberToBeDifferent = numberToBeDifferent;
        this.messageOnFailed = messageOnFailed;
    }

    @Override
    public Integer getParam() {
        return numberToBeDifferent;
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public Result check(Object object) {
        try{
            int  numberToCompare = Integer.parseInt(object.toString());

            return numberToCompare != numberToBeDifferent ? Result.success() : Result.failed(messageOnFailed);
        }catch (NumberFormatException | ClassCastException e) {
            return Result.failed(messageOnFailed);
        }
    }
}
