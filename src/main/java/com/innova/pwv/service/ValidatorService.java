package com.innova.pwv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.innova.pwv.util.ValidPair;
import com.innova.pwv.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * validator password service
 */
@Service
public class ValidatorService {
    @Autowired
    @Qualifier("charCaseValidator")
    private Validator charTypeValidator;
    @Autowired
    @Qualifier("lengthValidator")
    private Validator lengthValidator;
    @Autowired
    @Qualifier("sequenceValidator")
    private Validator sequenceValidator;
    /**
     *
     * @param password validate password
     * @return ValidPair
     */
    public ValidPair<Boolean, List<ValidPair<String, Boolean>>> valid(String password) {
        List<ValidPair<String, Boolean>> results = Stream.of(
                charTypeValidator.isValid(password),
                lengthValidator.isValid(password),
                sequenceValidator.isValid(password)
        ).collect(
                Collectors.toCollection(ArrayList::new)
        );
        long invalidCount = results.stream()
                .filter(p -> !p.right())
                .count();
        boolean isValid = invalidCount == 0;
        return new ValidPair<>(isValid, results);
    }
    /**
     * transforms the results to a JSON string
     *
     * @param results validate results
     * @return Json String
     */
    public String resultsToJsonString(ValidPair<Boolean, List<ValidPair<String, Boolean>>> results) {
        Map<String, String> validatorResultsAsMap = new HashMap<>();
        validatorResultsAsMap.put("Valid", results.left().toString());
        List<ValidPair<String, Boolean>> andWhyList = results.right();
        for (ValidPair<String, Boolean> componentResult : andWhyList) {
            validatorResultsAsMap.put(componentResult.left(), componentResult.right().toString());
        }
        String validatorResultsAsJsonString = results.left().toString();
        try {
            validatorResultsAsJsonString = new ObjectMapper().writeValueAsString(validatorResultsAsMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return validatorResultsAsJsonString;
    }
}
