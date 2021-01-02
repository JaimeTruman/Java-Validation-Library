package main;

import main.validators.Validator;

import java.util.*;

/**
 * Class design to validate the elements
 */
public final class ValidatorService {
    private ValidatorService () {}

    /**
     * Checks if an element passes all the validations.
     * @param element to check
     * @param validators to check
     * @return ValidationResult if one validation is failed it returns the result of the check (message on failed). If
     * every validation is passed it return an successful result.
     */
    public static ValidationResult validate (Object element, Validator... validators) {
        for(Validator validator : validators){
            ValidationResult result = validator.check(element);

            if(result.isFailed()){
                return result;
            }
        }

        return ValidationResult.success();
    }

    /**
     * It is the same as the method above. The only difference is the the type of validators in this case
     * it can be any collection
     */
    public static<E> ValidationResult validate(E element, Collection<? extends Validator> validators) {
        return validate(element, (Validator[]) validators.toArray());
    }

    /**
     * Checks if an element passes all the validations. If one validation is failed its error
     * message is added to an arraylist
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
     * It is the same as the method aboce. The only difference is the the type of validators in this case
     * it can be any collection
     */
    public static<E> List<String> validateAndGetErrors(E element, Collection<? extends Validator> validators) {
        return validateAndGetErrors(element, (Validator[]) validators.toArray());
    }

    /**
     * Takes an element to validate and a collection of validators to validate it. The goal of this method is to
     * start a chain of various elements with its validators. Example:
     * ValidationResult result = ValidatorService.startValidating(yourvariable1, yourValidationsArray1).and(yourvariable2, yourValidationsArray2).validateAll();
     * @param element to validate
     * @param validators to validate the element. It can be any collection that has validators
     * @return ValidationsBuilder class which is desiged to chain various validations of different elements. To chain more validations
     * you can use the method and(element, yourvalidations). And for get the result of the validtions your can use the method validateAll
     * or validateAllAndGetErrors which returns an ValidationResult
     */
    public static ValidationsBuilder startValidating (Object element, Collection<? extends Validator> validators) {
        List<Validator> validatorList = new ArrayList<>(validators);

        Map<Object, List<Validator>> elementsValidations = new HashMap<>();
        elementsValidations.put(element, validatorList);

        return new ValidationsBuilder(elementsValidations);
    }

    /**
     * This mehotd is the same as startValidating(element, collections of validators) (the method above). The only difference is
     * that this method take an array of validators instead of a collection
     */
    public static ValidationsBuilder startValidating (Object element, Validator... validators) {
        List<Validator> validatorList = Arrays.asList(validators);

        Map<Object, List<Validator>> elementsValidations = new HashMap<>();
        elementsValidations.put(element, validatorList);


        return new ValidationsBuilder(elementsValidations);
    }

    /**
     * The goal of this class is to provide a way to chain validations with various elements with its validators. Example:
     * ValidationResult result = ValidatorService.startValidating(yourvariable1, notnull, number).and(yourvariable2, notnull).validateAll();
     *
     * To start the chain you should use the static mehotd startValidating in ValidatorService. To chain more validations you can
     * use the method and() returned by startValidating mehotd.For last to start validating the chain your can use the methods validateAll
     * or validateAllAndGetErrors which is returned by and() or startValidating()
     */
    public static class ValidationsBuilder {
        // key: element, value: its validators
        private Map<Object, List<Validator>> elementsValidations;

        protected ValidationsBuilder(Map<Object, List<Validator>> elementsValidations) {
            this.elementsValidations = elementsValidations;
        }

        /**
         * Chain more validations
         * @param element to validate
         * @param validators of the element. It can be any collections which contains validators
         * @return ValidationsBuilder to chain more validations or start validating
         */
        public ValidationsBuilder and (Object element, Collection<? extends Validator> validators) {
            List<Validator> validatorList = new ArrayList<>(validators);
            this.elementsValidations.put(element, validatorList);

            return this;
        }

        /**
         * This is the same as the method above. The only difference is that it takes an array of validators
         */
        public ValidationsBuilder and (Object element, Validator... validators) {
            List<Validator> validatorList = Arrays.asList(validators);
            this.elementsValidations.put(element, validatorList);

            return this;
        }

        /**
         * Validate all the chain of elements with its validators. When one element is failed it returns
         * the Validation result of the failed element.
         * @return ValidationResult of the first element that fails the validation
         */
        public ValidationResult validateAll () {
            Object element;
            List<Validator> validatorsToObject;

            for(Map.Entry<Object, List<Validator>> entry : elementsValidations.entrySet()){;
                element = entry.getKey();
                validatorsToObject = entry.getValue();

                for(Validator validator : validatorsToObject){
                    ValidationResult validationResult = validator.check(element);

                    if(validationResult.isFailed()){
                        return ValidationResult.failed(validationResult.getMessage());
                    }
                }
            }

            return ValidationResult.success();
        }

        /**
         * Validate all the chain. When the validation of an element is failed, the message error is stored
         * in a arraylist.
         * @return List<String> of the error messages. If all the validations have passed it return an empty arryalist
         */
        public List<String> validateAllAndGetErrors () {
            List<String> messageErrors = new ArrayList<>();

            Object element;
            List<Validator> validatorsToObject;

            for(Map.Entry<Object, List<Validator>> entry : elementsValidations.entrySet()){
                element = entry.getKey();
                validatorsToObject = entry.getValue();

                for(Validator validator : validatorsToObject){
                    ValidationResult validationResult = validator.check(element);

                    if(validationResult.isFailed()){
                        messageErrors.add(validationResult.getMessage());
                    }
                }
            }

            return messageErrors;
        }
    }
}
