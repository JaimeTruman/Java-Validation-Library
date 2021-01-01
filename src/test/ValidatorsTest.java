package test;


import main.ValidatorService;
import main.results.ValidationResult;
import main.validators.Validator;
import main.validators.allvalidators.classes.InstanceOf;
import main.validators.allvalidators.numbers.MaxValue;
import main.validators.allvalidators.strings.MaxLength;
import main.validators.allvalidators.strings.MinLength;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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

        List<String> errors2 = ValidatorService.validateAndGetErrors("hola", minLength, maxLength, instanceOfString, maxValue);
        Assert.assertEquals(2, errors2.size());
    }
}
