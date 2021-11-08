package com.innominds.assignment.shipmentservice.controller;

import com.innominds.assignment.shipmentservice.service.ShipmentService;
import com.innominds.assignment.shipmentservice.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc
public class ShipmentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ShipmentService service;

    @Test
    public void getShipment() throws Exception {

        Mockito.when(service.getShipment("USPS","1234")).thenReturn(TestUtil.buildGetResponse("USPS","1234","200"));
        mockMvc.perform(MockMvcRequestBuilders.get("/tracking/{slug}/{trackNum}","USPS","1234")
                    .accept(MediaType.APPLICATION_JSON))
                   // .content(objectMapper.writeValueAsString(userJohn))
                    .andExpect(status().isOk()).andExpect(jsonPath("$.meta.code").value("200"));
    }

    //TODO more test scenario to write
}
