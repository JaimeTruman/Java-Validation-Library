package test.validators;

import main.ValidationResult;
import main.ValidatorService;
import main.validators.strings.*;
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
        NotIncludeCharacters doesntIncludeCharacters = new NotIncludeCharacters(new Character[]{'f', 't', 'w'}, "error");
        ValidationResult result = ValidatorService.validate(textToTest, doesntIncludeCharacters);
        Assert.assertTrue(result.isSuccessful());

        NotIncludeCharacters doesntIncludeCharacters2 = new NotIncludeCharacters(new Character[]{'f', 'o', 'w'}, "error");
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

    @Test
    public void equalsIgnoreCaseTest() {
        EqualsIgnoreCase equalsIgnoreCase = new EqualsIgnoreCase("error");
        ValidationResult result = ValidatorService.validate("hola", equalsIgnoreCase.of("hola"));
        Assert.assertTrue(result.isSuccessful());

        ValidationResult result2 = ValidatorService.validate("hola", equalsIgnoreCase.of("hoñ"));
        Assert.assertTrue(result2.isFailed());
    }

    @Test
    public void notEqualsIgnoreCaseTest() {
        NotEqualsIgnoreCase notEqualsIgnoreCase = new NotEqualsIgnoreCase("error");
        ValidationResult result = ValidatorService.validate("hola", notEqualsIgnoreCase.of("12"));
        Assert.assertTrue(result.isSuccessful());

        ValidationResult result2 = ValidatorService.validate("hola", notEqualsIgnoreCase.of("hola"));
        Assert.assertTrue(result2.isFailed());
    }
}
