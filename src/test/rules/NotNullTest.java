package test.rules;

import main.Validations;
import main.results.Result;
import main.rules.allrules.NotNull;
import org.junit.Assert;
import org.junit.Test;

public class NotNullTest {
    private Object nullObject;

    @Test
    public void notNullTest () {
        NotNull notNull = new NotNull("error");
        Result result = Validations.evaluate(new Object(), notNull);
        Assert.assertTrue(result.isSuccessful());

        Result result2 = Validations.evaluate(null, notNull);
        Assert.assertTrue(result2.isFailed());
    }
}
