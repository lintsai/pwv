package com.innova.pwv.component;

import com.innova.pwv.util.ValidPair;
import com.innova.pwv.util.Validator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;


/**
 * PasswordValidatorComponentDecorator validator check password sequence is valid
 */
@Component
public class SequenceValidator implements Validator {
    public static final String VALIDATOR_WORD =
            "Must not contain any sequence of characters immediately followed by the same sequence.";
    public static final String REGEX = "(.+?)(\\1+?)";
    /**
     * check password length sequence is valid
     *
     * @param password validate password
     * @return ValidPair with boolean pass or fail
     */
    public ValidPair<String, Boolean> isValid(String password) {
        return new ValidPair<>(
                VALIDATOR_WORD,
                !Pattern.compile(REGEX)
                        .matcher(password)
                        .find()
        );
    }
}
