package com.innova.pwv.util;

/**
 * interface for Validator
 */
public interface Validator {
    ValidPair<String, Boolean> isValid(String password);
}
