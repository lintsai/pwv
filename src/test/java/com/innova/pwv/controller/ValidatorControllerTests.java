package com.innova.pwv.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * test controller /validator set password to validate
 */
@AutoConfigureMockMvc
@SpringBootTest
public class ValidatorControllerTests {
    @Autowired
    private MockMvc mockMvc;

    /**
     * success test
     * @throws Exception http get exception
     */
    @Test
    void success() throws Exception {
        mockMvc.perform(get("/validator?password=123abc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Valid").value("true"));
    }

    /**
     * check char case fail test
     * @throws Exception http get exception
     */
    @Test
    void charCaseFail() throws Exception {
        mockMvc.perform(get("/validator?password=123abC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Valid").value("false"));
    }

    /**
     * check length fail test
     * @throws Exception http get exception
     */
    @Test
    void lengthFail() throws Exception {
        mockMvc.perform(get("/validator?password=12a"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Valid").value("false"));
    }

    /**
     * check sequence fail test
     * @throws Exception http get exception
     */
    @Test
    void sequenceFail() throws Exception {
        mockMvc.perform(get("/validator?password=123abb"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Valid").value("false"));
    }
}
