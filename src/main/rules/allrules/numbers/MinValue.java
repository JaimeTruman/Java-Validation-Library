package main.rules.allrules.numbers;

import main.results.Result;
import main.rules.OneParam;

public class MinValue implements OneParam {
    private final String messageOnFailed;
    private final int minValue;

    public MinValue(int minValue, String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
        this.minValue = minValue;
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public Integer getParam() {
        return minValue;
    }

    @Override
    public Result check(Object element) {
        try{
            double number = Double.parseDouble(element.toString());

            if(number < minValue){
                return Result.failed(messageOnFailed);
            }else{
                return Result.success();
            }
        }catch (NumberFormatException | ClassCastException e) {
            return Result.failed(messageOnFailed);
        }
    }
}
