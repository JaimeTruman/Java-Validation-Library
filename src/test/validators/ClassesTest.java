package test.validators;

import main.ValidatorService;
import main.ValidationResult;
import main.validators.classes.InstanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class ClassesTest {
    private String classToTest;

    @Before
    public void setUp () {
        this.classToTest = "prueba";
    }

    @Test
    public void instanceOfTest () {
        InstanceOf instanceOfCheck1 = new InstanceOf(String.class, "error");
        ValidationResult result = ValidatorService.validate(classToTest, instanceOfCheck1);
        Assert.assertTrue(result.isSuccessful());

        InstanceOf instanceOfCheck2 = new InstanceOf(Date.class, "error");
        ValidationResult result2 = ValidatorService.validate(classToTest, instanceOfCheck2);
        Assert.assertTrue(result2.isFailed());
    }
}
