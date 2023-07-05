package com.innova.pwv.service;

import com.innova.pwv.util.ValidPair;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * test validator password service
 */
@SpringBootTest
public class ValidatorServiceTests {
    @Autowired
    private ValidatorService validatorService;

    /**
     * success test
     * @throws JSONException json parse exception
     */
    @Test
    public void success() throws JSONException {
        String password = "123abc";
        ValidPair<Boolean, List<ValidPair<String, Boolean>>> validationResults =
                validatorService.valid(password);
        String results =
                validatorService.resultsToJsonString(validationResults);
        Assertions.assertTrue(Boolean.parseBoolean(new JSONObject(results).get("Valid").toString()));
    }

    /**
     * check char case fail test
     * @throws JSONException json parse exception
     */
    @Test
    public void charCaseFail() throws JSONException {
        String password = "123abC";
        ValidPair<Boolean, List<ValidPair<String, Boolean>>> validationResults =
                validatorService.valid(password);
        String results =
                validatorService.resultsToJsonString(validationResults);
        Assertions.assertFalse(Boolean.parseBoolean(new JSONObject(results).get("Valid").toString()));
    }

    /**
     * check length fail test
     * @throws JSONException json parse exception
     */
    @Test
    public void lengthFail() throws JSONException {
        String password = "12a";
        ValidPair<Boolean, List<ValidPair<String, Boolean>>> validationResults =
                validatorService.valid(password);
        String results =
                validatorService.resultsToJsonString(validationResults);
        Assertions.assertFalse(Boolean.parseBoolean(new JSONObject(results).get("Valid").toString()));
    }

    /**
     * check sequence fail test
     * @throws JSONException json parse exception
     */
    @Test
    public void sequenceFail() throws JSONException {
        String password = "123abb";
        ValidPair<Boolean, List<ValidPair<String, Boolean>>> validationResults =
                validatorService.valid(password);
        String results =
                validatorService.resultsToJsonString(validationResults);
        Assertions.assertFalse(Boolean.parseBoolean(new JSONObject(results).get("Valid").toString()));
    }
}
