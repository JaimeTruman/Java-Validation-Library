package main.rules.allrules.strings;

import main.results.Result;
import main.rules.OneParam;

public class MinLength implements OneParam {
    private final String messageOnFailed;
    private final int minLength;

    public MinLength(int minLength, String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
        this.minLength = minLength;
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public Integer getParam() {
        return minLength;
    }

    @Override
    public Result check(Object element) {
        try{
            String elementToCompare = element.toString();

            if(elementToCompare.length() < minLength){
                return Result.failed(messageOnFailed);
            }else{
                return Result.success();
            }
        }catch (NumberFormatException | ClassCastException e) {
            return Result.failed(messageOnFailed);
        }
    }
}
