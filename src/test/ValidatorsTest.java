package test;


import main.ValidationResult;
import main.ValidatorService;
import main.validators.Validator;
import main.validators.classes.InstanceOf;
import main.validators.numbers.MaxValue;
import main.validators.numbers.Same;
import main.validators.strings.MaxLength;
import main.validators.strings.MinLength;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ValidatorsTest {
    private MaxLength maxLength;
    private MinLength minLength;
    private InstanceOf instanceOfString;
    private MaxValue maxValue;

    @Before
    public void setup () {
        this.maxLength = new MaxLength(20, "maxlength");
        this.minLength = new MinLength(10, "minlength");
        this.maxValue = new MaxValue(10, "Valor maximo alcanzado");
        this.instanceOfString = new InstanceOf(String.class, "instanceof");
    }

    @Test
    public void validateArrayTest() {
        ValidationResult result = ValidatorService.validate("hola soy jaime", minLength, maxLength, instanceOfString);
        Assert.assertTrue(result.isSuccessful());

        ValidationResult result2 = ValidatorService.validate("hola", minLength, maxLength, instanceOfString);
        Assert.assertTrue(result2.isFailed());
    }

    @Test
    public void validateCollectionTest() {
        List<Validator> validators = Arrays.asList(minLength, maxLength, instanceOfString);

        ValidationResult result = ValidatorService.validate("hola soy jaime", validators);
        Assert.assertTrue(result.isSuccessful());

        ValidationResult result2 = ValidatorService.validate("hola", validators);
        Assert.assertTrue(result2.isFailed());
    }

    @Test
    public void validateAndGetErrorsTest() {
        List<String> errors = ValidatorService.validateAndGetErrors("hola soy jaime", minLength, maxLength, instanceOfString);
        Assert.assertTrue(errors.isEmpty());

        List<String> errors2 = ValidatorService.validateAndGetErrors("hola", Arrays.asList(minLength, maxLength, instanceOfString, maxValue));
        Assert.assertEquals(2, errors2.size());
    }

    @Test
    public void validateMayThrowException () {
        int[] array = new int[]{1, 2, 3};

        ValidationResult result = ValidatorService.validateMayThrowException(() -> array[4], "error", minLength.of(1));
        Assert.assertTrue(result.isFailed());
    }

    @Test
    public void validateMayThrowExceptionValidatorBuilderTest() {
        int[] array = new int[]{1, 2, 3};

        ValidationResult result = ValidatorService.startValidating("hola", minLength.of(1))
                .andMayThrowException(() -> array[3], "exception", minLength.of(1))
                .validateAll();

        Assert.assertTrue(result.isFailed());
        Assert.assertEquals("exception", result.getMessage());
    }

    @Test
    public void validateAndAsync () {
        String hola = "hola";

        ValidationResult result = ValidatorService.startValidating(hola, minLength.of(2))
                .andAsynch(hola::toUpperCase, Executors.newFixedThreadPool(1), "error", minLength.of(2))
                .validateAll();

        Assert.assertTrue(result.isSuccessful());
    }

    @Test
    public void startValidatingAndValidateAllTest() {
        List<String> errors = ValidatorService.startValidating("hola", minLength.of(1), maxLength.of(10)).and("hola2", minLength.of(10)).validateAllAndGetErrors();
        Assert.assertEquals(1, errors.size());

        ValidationResult result = ValidatorService.startValidating("hola", minLength.of(10), maxLength.of(10)).and("hola2", minLength.of(10)).validateAll();
        Assert.assertTrue(result.isFailed());
    }
}
