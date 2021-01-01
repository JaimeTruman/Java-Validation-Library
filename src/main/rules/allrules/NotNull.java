package main.rules.allrules;

import main.results.Result;
import main.rules.NoParam;

public class NotNull implements NoParam {
    private final String messageOnFailed;

    public NotNull(String messageOnFailed) {
        this.messageOnFailed = messageOnFailed;
    }

    @Override
    public String getMessageOnFailed() {
        return messageOnFailed;
    }

    @Override
    public Result check(Object object) {
        return object == null ? Result.failed(messageOnFailed) : Result.success();
    }
}
