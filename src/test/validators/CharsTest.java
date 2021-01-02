package test.validators;

import main.ValidationResult;
import main.ValidatorService;
import main.validators.chars.MatchCharacters;
import org.junit.Assert;
import org.junit.Test;

public class CharsTest {
    @Test
    public void MatchCharactersTest () {
        MatchCharacters includeCharacters = new MatchCharacters(new Character[]{'A', 'B', 'C'}, "Error");
        ValidationResult result = ValidatorService.startValidating('A', includeCharacters).validateAll();
        Assert.assertTrue(result.isSuccessful());

        ValidationResult result2 = ValidatorService.startValidating('D', includeCharacters).validateAll();
        Assert.assertTrue(result2.isFailed());
    }
}
