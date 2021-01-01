package test.validators;

import main.ValidatorService;
import main.results.ValidationResult;
import main.validators.allvalidators.strings.IncludeCharacters;
import main.validators.allvalidators.strings.Matches;
import main.validators.allvalidators.strings.MaxLength;
import main.validators.allvalidators.strings.MinLength;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringsTest {
    private String textToTest;

    @Before
    public void setUp () {
        this.textToTest = "hola";
    }

    @Test
    public void doestIncludeCharactersTest () {
        IncludeCharacters doesntIncludeCharacters = new IncludeCharacters(new Character[]{'f', 't', 'w'}, "error");
        ValidationResult result = ValidatorService.validate(textToTest, doesntIncludeCharacters);
        Assert.assertTrue(result.isSuccessful());

        IncludeCharacters doesntIncludeCharacters2 = new IncludeCharacters(new Character[]{'f', 'o', 'w'}, "error");
        ValidationResult result2 = ValidatorService.validate(textToTest, doesntIncludeCharacters2);
        Assert.assertTrue(result2.isFailed());
    }

    @Test
    public void matchesTest () {
        Matches matches = new Matches("(.*)l(.*)","error");
        ValidationResult result = ValidatorService.validate(textToTest, matches);
        Assert.assertTrue(result.isSuccessful());

        ValidationResult result2 = ValidatorService.validate("test", matches);
        Assert.assertTrue(result2.isFailed());
    }

    @Test
    public void maxLengthTest() {
        MaxLength maxLength = new MaxLength(10, "error");
        ValidationResult result = ValidatorService.validate(textToTest, maxLength);
        Assert.assertTrue(result.isSuccessful());

        ValidationResult result2 = ValidatorService.validate("hoalohaskhalskhalkhs", maxLength);
        Assert.assertTrue(result2.isFailed());
    }

    @Test
    public void minLengthTest() {
        MinLength minLength = new MinLength(4, "error");
        ValidationResult result = ValidatorService.validate(textToTest, minLength);
        Assert.assertTrue(result.isSuccessful());

        ValidationResult result2 = ValidatorService.validate("a", minLength);
        Assert.assertTrue(result2.isFailed());
    }
}
