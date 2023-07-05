package com.innova.pwv.util;

public interface Validator {
    ValidPair<String, Boolean> isValid(String password);
}
