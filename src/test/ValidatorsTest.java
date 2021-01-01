package test;


import main.Validations;
import main.results.Result;
import main.rules.allrules.classes.InstanceOf;
import main.rules.allrules.numbers.MaxValue;
import main.rules.allrules.strings.MaxLength;
import main.rules.allrules.strings.MinLength;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void evaluate () {
        Result result = Validations.evaluate("hola soy jaime", minLength, maxLength, instanceOfString);
        Assert.assertTrue(result.isSuccessful());

        Result result2 = Validations.evaluate("hola", minLength, maxLength, instanceOfString);
        Assert.assertTrue(result2.isFailed());
    }

    @Test
    public void evaluateAndGetErrors () {
        List<String> errors = Validations.evaluateAndGetErrors("hola soy jaime", minLength, maxLength, instanceOfString);
        Assert.assertTrue(errors.isEmpty());

        List<String> errors2 = Validations.evaluateAndGetErrors("hola", minLength, maxLength, instanceOfString, maxValue);
        Assert.assertEquals(2, errors2.size());
    }
}
