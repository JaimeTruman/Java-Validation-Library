package test.rules;

import main.Validations;
import main.results.Result;
import main.rules.allrules.classes.InstanceOf;
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
        Result result = Validations.evaluate(classToTest, instanceOfCheck1);
        Assert.assertTrue(result.isSuccessful());

        InstanceOf instanceOfCheck2 = new InstanceOf(Date.class, "error");
        Result result2 = Validations.evaluate(classToTest, instanceOfCheck2);
        Assert.assertTrue(result2.isFailed());
    }
}
