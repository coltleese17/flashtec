package com.leesec.flashtractproject.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leesec.flashtractproject.entity.contract.Contract;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ContractControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void test_post_contract_success() throws Exception {
        Contract contractToPost = buildContract();

        Contract responseContract = objectMapper.readValue(this.mockMvc.perform(MockMvcRequestBuilders.post("/contract")
                        .content(objectMapper.writeValueAsString(contractToPost))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString(), Contract.class);

        assertEquals(responseContract.getDescription(), contractToPost.getDescription());
    }

    @Test
    public void test_get_contract_expecting_error_message() throws Exception {
        String expectedJson = "{\"message\" : \"Contract not found with given ID\"}";

        mockMvc.perform(MockMvcRequestBuilders.get("/contract/999"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void test_get_contract_after_post_expecting_success() throws Exception {
        //Confirm there is no contract with ID 1
        String expectedJson = "{\"message\" : \"Contract not found with given ID\"}";

        mockMvc.perform(MockMvcRequestBuilders.get("/contract/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(expectedJson));

        //Post Contract
        Contract contractToPost = buildContract();

        Contract responseContract = objectMapper.readValue(this.mockMvc.perform(MockMvcRequestBuilders.post("/contract")
                        .content(objectMapper.writeValueAsString(contractToPost))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString(), Contract.class);

        //Get Contract
        Contract expectedContract = objectMapper.readValue(this.mockMvc.perform(MockMvcRequestBuilders.get("/contract/1"))
                .andReturn().getResponse().getContentAsString(), new TypeReference<Contract>() {
        });

        assertEquals(expectedContract.getClientId(), contractToPost.getClientId());
        assertEquals(expectedContract.getStartDate(), contractToPost.getStartDate());
    }

    private Contract buildContract() {
        LocalDateTime now = LocalDateTime.now();
        return Contract.builder()
                .clientId(1L)
                .vendorId(2L)
                .totalCost(1000.00)
                .remainingCost(1000.00)
                .startDate(now)
                .endDate(now.plusDays(7))
                .build();
    }
}
