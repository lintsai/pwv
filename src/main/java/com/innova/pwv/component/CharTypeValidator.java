package com.innova.pwv.component;

import com.innova.pwv.util.Valid;
import com.innova.pwv.util.Validator;
import org.springframework.stereotype.Component;


/**
 * PasswordValidatorDecorator validator check password length chars type is valid
 */
@Component
public class CharTypeValidator implements Validator {
    public static final String VALIDATOR_WORD =
            "Must consist of a mixture of lowercase letters and numerical digits only, " +
                    "with at least one of each.";
    public static final String REGEX = "^(?=.*[a-z])(?=.*\\d)[a-z\\d]*$";
    /**
     * check password length chars type is valid
     *
     * @param password validate password
     * @return boolean pass or fail
     */
    public Valid<String, Boolean> isValid(String password) {
        return new Valid<>(
                VALIDATOR_WORD,
                password.matches(REGEX)
        );
    }
}
