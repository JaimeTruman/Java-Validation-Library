package test.validators;

import main.ValidatorService;
import main.ValidationResult;
import main.validators.chars.MatchCharacters;
import org.junit.Assert;
import org.junit.Test;

public class CharsTest {
    @Test
    public void MatchCharactersTest () {
        MatchCharacters includeCharacters = new MatchCharacters(new Character[]{'A', 'B', 'C'}, "Error");
        ValidationResult result = ValidatorService.validate('A', includeCharacters);
        Assert.assertTrue(result.isSuccessful());

        ValidationResult result2 = ValidatorService.validate('D', includeCharacters);
        Assert.assertTrue(result2.isFailed());
    }
}
