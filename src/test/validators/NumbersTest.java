package test.validators;

import main.ValidatorService;
import main.ValidationResult;
import main.validators.numbers.*;
import main.validators.numbers.Number;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NumbersTest {
    private int numberToTest;
    private String textToTest;

    @Before
    public void setUp () {
        this.numberToTest = 10;
        this.textToTest = "hola";
    }

    @Test
    public void differentTest () {
        Different different = new Different(1, "error");
        ValidationResult result = ValidatorService.validate(numberToTest, different);
        Assert.assertTrue(result.isSuccessful());


        Different different2 = new Different(10, "error");
        ValidationResult result2 = ValidatorService.validate(numberToTest, different2);
        Assert.assertTrue(result2.isFailed());
    }

    @Test
    public void isNaturalNumberTest () {
        NaturalNumber isNaturalNumber = new NaturalNumber("error");
        ValidationResult result = ValidatorService.validate(numberToTest, isNaturalNumber);
        Assert.assertTrue(result.isSuccessful());

        ValidationResult result2 = ValidatorService.validate(1.1, isNaturalNumber);
        Assert.assertTrue(result2.isFailed());
    }

    @Test
    public void isNumberTest () {
        Number isNumber = new Number("Error");
        ValidationResult result = ValidatorService.validate(numberToTest, isNumber);
        Assert.assertTrue(result.isSuccessful());

        ValidationResult result2 = ValidatorService.validate("test", isNumber);
        Assert.assertTrue(result2.isFailed());
    }

    @Test
    public void isPositiveNumberTest () {
        PositiveNumber isPositiveNumber = new PositiveNumber("error");
        ValidationResult result = ValidatorService.validate(numberToTest, isPositiveNumber);
        Assert.assertTrue(result.isSuccessful());

        ValidationResult result2 = ValidatorService.validate(-1, isPositiveNumber);
        Assert.assertTrue(result2.isFailed());
    }

    @Test
    public void maxValueText () {
        MaxValue maxValue = new MaxValue(20, "error");
        ValidationResult result = ValidatorService.validate(numberToTest, maxValue);
        Assert.assertTrue(result.isSuccessful());


        MaxValue maxValue2 = new MaxValue(5, "error");
        ValidationResult result2 = ValidatorService.validate(numberToTest, maxValue2);
        Assert.assertTrue(result2.isFailed());
    }

    @Test
    public void minValueText () {
        MinValue minValue = new MinValue(5, "error");
        ValidationResult result = ValidatorService.validate(numberToTest, minValue);
        Assert.assertTrue(result.isSuccessful());

        MinValue minValue2 = new MinValue(30, "error");
        ValidationResult result2 = ValidatorService.validate(numberToTest, minValue2);
        Assert.assertTrue(result2.isFailed());
    }
}
