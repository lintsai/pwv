package com.innova.pwv.component;

import com.innova.pwv.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * test to check password length is valid
 */
@SpringBootTest
public class LengthValidatorTests {
    @Autowired
    @Qualifier("lengthValidator")
    private Validator lengthValidator;

    /**
     * '1a' not match, 2 words
     * '12a' not match, 3 words
     * '123a' not match, 4 words
     * '123ab' match, 5 words (in 5~12)
     * '123abc' match, 6 words (in 5~12)
     * '123aBC321cba' match, 12 words (in 5~12)
     * '123aBC321cba1' not match, 13 words
     * '' not match, cause is empty
     */
    @Test
    public void match() {
        Assertions.assertAll(
                () -> Assertions.assertFalse(lengthValidator.isValid("1a").right()),
                () -> Assertions.assertFalse(lengthValidator.isValid("12a").right()),
                () -> Assertions.assertFalse(lengthValidator.isValid("123a").right()),
                () -> Assertions.assertTrue(lengthValidator.isValid("123ab").right()),
                () -> Assertions.assertTrue(lengthValidator.isValid("123abc").right()),
                () -> Assertions.assertTrue(lengthValidator.isValid("123aBC321cba").right()),
                () -> Assertions.assertFalse(lengthValidator.isValid("123aBC321cba1").right()),
                () -> Assertions.assertFalse(lengthValidator.isValid("").right())
        );
    }
}
