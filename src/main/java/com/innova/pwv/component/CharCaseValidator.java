package com.innova.pwv.component;

import com.innova.pwv.util.ValidPair;
import com.innova.pwv.util.Validator;
import org.springframework.stereotype.Component;


/**
 * PasswordValidatorDecorator validator check password chars type is valid
 */
@Component
public class CharCaseValidator implements Validator {
    public static final String VALIDATOR_WORD =
            "Must consist of a mixture of lowercase letters and numerical digits only, " +
                    "with at least one of each.";
    public static final String REGEX = "^(?=.*[a-z])(?=.*\\d)[a-z\\d]*$";
    /**
     * check password length chars type is valid
     *
     * @param password validate password
     * @return ValidPair with boolean pass or fail
     */
    public ValidPair<String, Boolean> isValid(String password) {
        return new ValidPair<>(
                VALIDATOR_WORD,
                password.matches(REGEX)
        );
    }
}
