package main.rules.allrules.strings;

import main.results.Result;
import main.rules.OneParam;

public class MaxLength implements OneParam {
    private final String messageOnFailed;
    private final int maxLength;

    public MaxLength(int maxLength, String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
        this.maxLength = maxLength;
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public Object getParam() {
        return maxLength;
    }

    @Override
    public Result check(Object element) {
        try{
            String elementToCompare = element.toString();

            if(elementToCompare.length() > maxLength){
                return Result.failed(messageOnFailed);
            }else{
                return Result.success();
            }
        }catch (NumberFormatException | ClassCastException e) {
            return Result.failed(messageOnFailed);
        }
    }
}
