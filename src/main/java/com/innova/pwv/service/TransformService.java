package com.innova.pwv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.innova.pwv.util.ValidPair;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * transform data service
 */
@Service
public class TransformService {
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
