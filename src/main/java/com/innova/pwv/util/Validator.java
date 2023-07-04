package com.innova.pwv.util;

public interface Validator {
    Valid<String, Boolean> isValid(String password);
}
