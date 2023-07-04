package com.innova.pwv.component;

import com.innova.pwv.util.Valid;
import com.innova.pwv.util.Validator;
import org.springframework.stereotype.Component;


/**
 * PasswordValidator validator check password length
 */
@Component
public class LengthValidator implements Validator {
    public static final String VALIDATOR_WORD =
            "Must be between 5 and 12 characters in length.";
    public static final int LENGTH_MIN = 5;
    public static final int LENGTH_MAX = 12;
    /**
     * check password length
     *
     * @param password validate password
     * @return boolean pass or fail
     */
    public Valid<String, Boolean> isValid(String password) {
        return new Valid<>(
                VALIDATOR_WORD,
                (password.length() >= LENGTH_MIN)
                        & (password.length() <= LENGTH_MAX)
        );
    }
}