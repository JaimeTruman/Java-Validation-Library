package main;

import main.results.Failed;
import main.results.Result;
import main.rules.Rule;

import java.util.ArrayList;
import java.util.List;

public final class Validations {
    private Validations() {}

    /**
     * Checks if an element passes all the rules.
     * @param element to check
     * @param rules to check
     * @return if one rule is failed it returns the result of the check (message on failed). If
     * every rule is passed it return an successful result.
     */
    public static<E> Result evaluate(E element, Rule... rules) {
        for(Rule rule : rules){
            Result result = rule.check(element);

            if(result.isFailed()){
                return result;
            }
        }

        return Result.success();
    }

    /**
     * Checks if an element passes all the rules. If one rule is failed its error
     * failed message is added to an arraylist
      * @param element to check
     * @param rules to check
     * @return the messages of the failed checks
     */
    public static<E> List<String> evaluateAndGetErrors(E element, Rule... rules) {
        List<String> allFailedMessagesChecks = new ArrayList<>();

        for(Rule rule : rules){
            Result result = rule.check(element);

            if(result.isFailed()){
                Failed failedCheck = (Failed) result;

                allFailedMessagesChecks.add(failedCheck.getMessage());
            }
        }

        return allFailedMessagesChecks;
    }
}
