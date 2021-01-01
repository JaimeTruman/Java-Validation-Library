package test;


import main.ValidatorService;
import main.results.ValidationResult;
import main.validators.allvalidators.numbers.MaxValue;
import main.validators.allvalidators.numbers.MinValue;
import main.validators.allvalidators.numbers.NaturalNumber;
import main.validators.allvalidators.numbers.Number;
import org.junit.Assert;
import org.junit.Test;

public class UseCases {
    private final Number number = new Number("It has to be a number");
    private final NaturalNumber naturalNumber = new NaturalNumber("It has to be a natural number");
    private final MaxValue maxValue = new MaxValue("Too large");
    private final MinValue minValue = new MinValue("Too small");

    @Test
    public void useCaseNumbers () {
        ValidationResult result = ValidatorService.validate(20, number, naturalNumber, maxValue.of(50), minValue.of(10));
        Assert.assertTrue(result.isSuccessful());
    }
}
