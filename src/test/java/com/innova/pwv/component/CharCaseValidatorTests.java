package com.innova.pwv.component;

import com.innova.pwv.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * test to check password chars type is valid
 */
@SpringBootTest
public class CharCaseValidatorTests {
    @Autowired
    @Qualifier("charCaseValidator")
    private Validator charCaseValidator;

    /**
     * '1a' match
     * '12a' match
     * '123a' match
     * '123ab' match
     * 'ab' not match, cause no numerical digits
     * 'aB' not match, cause no numerical digits and not all lowercase letters
     * '1aB' not match, cause not all lowercase letters
     * '' not match, cause is empty
     */
    @Test
    public void match() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(charCaseValidator.isValid("1a").right()),
                () -> Assertions.assertTrue(charCaseValidator.isValid("12a").right()),
                () -> Assertions.assertTrue(charCaseValidator.isValid("123a").right()),
                () -> Assertions.assertTrue(charCaseValidator.isValid("123ab").right()),
                () -> Assertions.assertFalse(charCaseValidator.isValid("ab").right()),
                () -> Assertions.assertFalse(charCaseValidator.isValid("aB").right()),
                () -> Assertions.assertFalse(charCaseValidator.isValid("1aB").right()),
                () -> Assertions.assertFalse(charCaseValidator.isValid("").right())
        );
    }
}
