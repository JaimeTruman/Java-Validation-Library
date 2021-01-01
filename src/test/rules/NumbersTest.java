package test.rules;

import main.Validations;
import main.results.Result;
import main.rules.allrules.numbers.*;
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
        Result result = Validations.evaluate(numberToTest, different);
        Assert.assertTrue(result.isSuccessful());


        Different different2 = new Different(10, "error");
        Result result2 = Validations.evaluate(numberToTest, different2);
        Assert.assertTrue(result2.isFailed());
    }

    @Test
    public void isNaturalNumberTest () {
        IsNaturalNumber isNaturalNumber = new IsNaturalNumber("error");
        Result result = Validations.evaluate(numberToTest, isNaturalNumber);
        Assert.assertTrue(result.isSuccessful());

        Result result2 = Validations.evaluate(1.1, isNaturalNumber);
        Assert.assertTrue(result2.isFailed());
    }

    @Test
    public void isNumberTest () {
        IsNumber isNumber = new IsNumber("Error");
        Result result = Validations.evaluate(numberToTest, isNumber);
        Assert.assertTrue(result.isSuccessful());

        Result result2 = Validations.evaluate("test", isNumber);
        Assert.assertTrue(result2.isFailed());
    }

    @Test
    public void isPositiveNumberTest () {
        IsPositiveNumber isPositiveNumber = new IsPositiveNumber("error");
        Result result = Validations.evaluate(numberToTest, isPositiveNumber);
        Assert.assertTrue(result.isSuccessful());

        Result result2 = Validations.evaluate(-1, isPositiveNumber);
        Assert.assertTrue(result2.isFailed());
    }

    @Test
    public void maxValueText () {
        MaxValue maxValue = new MaxValue(20, "error");
        Result result = Validations.evaluate(numberToTest, maxValue);
        Assert.assertTrue(result.isSuccessful());


        MaxValue maxValue2 = new MaxValue(5, "error");
        Result result2 = Validations.evaluate(numberToTest, maxValue2);
        Assert.assertTrue(result2.isFailed());
    }

    @Test
    public void minValueText () {
        MinValue minValue = new MinValue(5, "error");
        Result result = Validations.evaluate(numberToTest, minValue);
        Assert.assertTrue(result.isSuccessful());

        MinValue minValue2 = new MinValue(30, "error");
        Result result2 = Validations.evaluate(numberToTest, minValue2);
        Assert.assertTrue(result2.isFailed());
    }
}
