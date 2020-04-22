package com.finra.assessment.controller;


import com.finra.assessment.service.MakeCombinations;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class CombinationGeneratorControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    MakeCombinations makeCombinations;
    @Test
    public void combinationsInvalidInputTest()throws Exception { // testing for invalid input string
        String sampleInput = "321";
        MvcResult mvcResult = this.mockMvc.perform(get("/" + sampleInput)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals("Invalid!", response.getContentAsString());
    }
    @Test
    public void combinationsValidInputTest()throws Exception { // testing for valid input string
        String sampleInput = "7654321";
        MvcResult mvcResult = this.mockMvc.perform(get("/" + sampleInput)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals("1335",new JSONObject(response.getContentAsString()).getString("count"));
    }

}
