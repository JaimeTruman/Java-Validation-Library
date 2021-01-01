package main.rules.allrules.numbers;

import main.results.Result;
import main.rules.OneParam;

public class MaxValue implements OneParam {
    private final String messageOnFailed;
    private final int maxValue;

    public MaxValue(int maxValue, String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
        this.maxValue = maxValue;
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public Integer getParam() {
        return maxValue;
    }

    @Override
    public Result check(Object element) {
        try{
            double number = Double.parseDouble(element.toString());

            if(number > maxValue){
                return Result.failed(messageOnFailed);
            }else{
                return Result.success();
            }
        }catch (NumberFormatException | ClassCastException e) {
            return Result.failed(messageOnFailed);
        }
    }
}
