package test.validators;

import main.ValidatorService;
import main.ValidationResult;
import main.validators.NotNull;
import org.junit.Assert;
import org.junit.Test;

public class NotNullTest {
    private Object nullObject;

    @Test
    public void notNullTest () {
        NotNull notNull = new NotNull("error");
        ValidationResult result = ValidatorService.validate(new Object(), notNull);
        Assert.assertTrue(result.isSuccessful());

        ValidationResult result2 = ValidatorService.validate(null, notNull);
        Assert.assertTrue(result2.isFailed());
    }
}
