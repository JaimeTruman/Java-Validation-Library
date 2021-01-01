package main.rules.allrules.numbers;

import main.results.Failed;
import main.results.Result;
import main.rules.NoParam;

public class IsNaturalNumber implements NoParam {
    private final String messageOnFailed;

    public IsNaturalNumber(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public Result check(Object element) {
        try{
            double number = Double.parseDouble(element.toString());

            boolean biggerThanZero = number >= 0;
            boolean notDecimal = number % 1 == 0;

            if(biggerThanZero && notDecimal)
                return Result.success();
            else
                return Result.failed(messageOnFailed);

        }catch (NumberFormatException | ClassCastException e) {
            return Failed.failed(messageOnFailed);
        }
    }
}
