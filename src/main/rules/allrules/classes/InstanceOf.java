package main.rules.allrules.classes;

import main.results.Result;
import main.rules.OneParam;

public class InstanceOf implements OneParam {
    private final Class<?> classToCheck;
    private final String messageOnFailed;

    public InstanceOf(Class<?> classToCheck, String messageOnFailed) {
        this.classToCheck = classToCheck;
        this.messageOnFailed = messageOnFailed;
    }

    @Override
    public Class<?> getParam() {
        return classToCheck;
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public Result check(Object object) {
        try {
            return classToCheck.isInstance(object) ? Result.success() : Result.failed(messageOnFailed);

        }catch (ClassCastException e) {
            return Result.failed(messageOnFailed);
        }
    }
}
