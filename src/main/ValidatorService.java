package main;

import main.validators.Validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class ValidatorService {
    private ValidatorService() {}

    /**
     * Checks if an element passes all the validations.
     * @param element to check
     * @param validators to check
     * @return if one validation is failed it returns the result of the check (message on failed). If
     * every validation is passed it return an successful result.
     */
    public static<E> ValidationResult validate(E element, Validator... validators) {
        for(Validator rule : validators){
            ValidationResult result = rule.check(element);

            if(result.isFailed()){
                return result;
            }
        }

        return ValidationResult.success();
    }

    /**
     * It is the same as the above method. The only difference is the the type of validators in this case
     * it can be any collection
     */
    public static<E> ValidationResult validate(E element, Collection<? extends Validator> validators) {
        return validate(element, (Validator[]) validators.toArray());
    }

    /**
     * Checks if an element passes all the validations. If one validation is failed its error
     * failed message is added to an arraylist
      * @param element to check
     * @param validators to check
     * @return the messages of the failed checks
     */
    public static<E> List<String> validateAndGetErrors(E element, Validator... validators) {
        List<String> allFailedMessagesChecks = new ArrayList<>();

        for(Validator validator : validators){
            ValidationResult result = validator.check(element);

            if(result.isFailed()){
                allFailedMessagesChecks.add(result.getMessage());
            }
        }

        return allFailedMessagesChecks;
    }

    /**
     * It is the same as the above method. The only difference is the the type of validators in this case
     * it can be any collection
     */
    public static<E> List<String> validateAndGetErrors(E element, Collection<? extends Validator> validators) {
        return validateAndGetErrors(element, (Validator[]) validators.toArray());
    }
}
