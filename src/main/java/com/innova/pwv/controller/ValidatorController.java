package com.innova.pwv.controller;

import com.innova.pwv.service.ValidatorService;
import com.innova.pwv.util.ValidPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * /validator set password to validate
 */
@RestController
public class ValidatorController {

    @Autowired
    private ValidatorService validatorService;

    /**
     * requests to the /validator to check the password
     *
     * @param password validate the password
     * @return a json string
     */
    @RequestMapping("/validator")
    public String validator(@RequestParam(value="password") String password) {
        ValidPair<Boolean, List<ValidPair<String, Boolean>>> results =
                validatorService.valid(password);
        return validatorService.resultsToJsonString(results);
    }

}
