package test.rules;

import main.Validations;
import main.results.Result;
import main.rules.allrules.strings.DoesntIncludeCharacters;
import main.rules.allrules.strings.Matches;
import main.rules.allrules.strings.MaxLength;
import main.rules.allrules.strings.MinLength;
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
        DoesntIncludeCharacters doesntIncludeCharacters = new DoesntIncludeCharacters(new Character[]{'f', 't', 'w'}, "error");
        Result result = Validations.evaluate(textToTest, doesntIncludeCharacters);
        Assert.assertTrue(result.isSuccessful());

        DoesntIncludeCharacters doesntIncludeCharacters2 = new DoesntIncludeCharacters(new Character[]{'f', 'o', 'w'}, "error");
        Result result2 = Validations.evaluate(textToTest, doesntIncludeCharacters2);
        Assert.assertTrue(result2.isFailed());
    }

    @Test
    public void matchesTest () {
        Matches matches = new Matches("(.*)l(.*)","error");
        Result result = Validations.evaluate(textToTest, matches);
        Assert.assertTrue(result.isSuccessful());

        Result result2 = Validations.evaluate("test", matches);
        Assert.assertTrue(result2.isFailed());
    }

    @Test
    public void maxLengthTest() {
        MaxLength maxLength = new MaxLength(10, "error");
        Result result = Validations.evaluate(textToTest, maxLength);
        Assert.assertTrue(result.isSuccessful());

        Result result2 = Validations.evaluate("hoalohaskhalskhalkhs", maxLength);
        Assert.assertTrue(result2.isFailed());
    }

    @Test
    public void minLengthTest() {
        MinLength minLength = new MinLength(4, "error");
        Result result = Validations.evaluate(textToTest, minLength);
        Assert.assertTrue(result.isSuccessful());

        Result result2 = Validations.evaluate("a", minLength);
        Assert.assertTrue(result2.isFailed());
    }
}
