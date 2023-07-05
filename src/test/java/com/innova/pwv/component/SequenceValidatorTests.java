package com.innova.pwv.component;

import com.innova.pwv.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * test to check password sequence is valid
 */
@SpringBootTest
public class SequenceValidatorTests {
    @Autowired
    @Qualifier("sequenceValidator")
    private Validator sequenceValidator;

    /**
     * '1a' match
     * '12a' match
     * '122a' not match, cause '22'
     * '123ab' match
     * '123abb' not match, cause 'bb'
     * '123aBb321cba' match
     * '123aBB321cba' not match, cause 'BB'
     * '123aBC321cba' match
     */
    @Test
    public void match() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(sequenceValidator.isValid("1a").right()),
                () -> Assertions.assertTrue(sequenceValidator.isValid("12a").right()),
                () -> Assertions.assertFalse(sequenceValidator.isValid("122a").right()),
                () -> Assertions.assertTrue(sequenceValidator.isValid("123ab").right()),
                () -> Assertions.assertFalse(sequenceValidator.isValid("123abb").right()),
                () -> Assertions.assertTrue(sequenceValidator.isValid("123aBb321cba").right()),
                () -> Assertions.assertFalse(sequenceValidator.isValid("123aBB321cba").right()),
                () -> Assertions.assertTrue(sequenceValidator.isValid("123aBC321cba").right())
        );
    }
}
