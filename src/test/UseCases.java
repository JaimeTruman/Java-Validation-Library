package test;


import main.ValidationResult;
import main.ValidatorService;
import main.validators.NotNull;
import main.validators.numbers.*;
import main.validators.numbers.Number;
import main.validators.strings.MinLength;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

import static main.ValidatorService.startValidating;


public class UseCases {
    private final PositiveNumber positiveNumber = new PositiveNumber("positiveNumber");
    private final Number number = new Number("It has to be a number");
    private final NaturalNumber naturalNumber = new NaturalNumber("It has to be a natural number");
    private final MaxValue maxValue = new MaxValue("Too large");
    private final MinValue minValue = new MinValue("Too small");
    private final MinLength minLength = new MinLength("Too small");
    private final NotNull notNull = new NotNull("Not null");
    private final Different different = new Different("diferente");
    private final Same same = new Same("different");

    @Test
    public void useCaseNumbers () {
        ValidationResult result = ValidatorService.startValidating(20, number, naturalNumber, maxValue.of(50), minValue.of(10)).validateAll();
        Assert.assertTrue(result.isSuccessful());
    }

    @Test
    public void useCaseManyValidations () {
        ValidationResult result2 = ValidatorService.startValidating(10, number, maxValue.of(50))
                .and("hosasasasasla", Arrays.asList(notNull, minLength.of(10)))
                .and(new Date(), notNull)
                .validateAll();
        Assert.assertTrue(result2.isSuccessful());
    }

    @Test
    public void useCase3 () {
        String[] args = new String[]{"Klyter", "123"};

        ValidationResult result = startValidating(args, notNull.message("notNull"))
                .and(args.length, same.as(2, "diferent"))
                .andMayThrowException(() -> args[1], "excepcion", positiveNumber)
                .validateAll();

        System.out.println(result.getMessage());

        Assert.assertTrue(result.isSuccessful());
    }
}
