package com.innominds.assignment.shipmentservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innominds.assignment.shipmentservice.config.TrackingServiceConfig;
import com.innominds.assignment.shipmentservice.exception.SomeProcessingException;
import com.innominds.assignment.shipmentservice.model.Shipment;
import com.innominds.assignment.shipmentservice.model.Tracking;
import com.innominds.assignment.shipmentservice.response.CustomResponse;
import com.innominds.assignment.shipmentservice.thirdParty.APIResponse;
import com.innominds.assignment.shipmentservice.thirdParty.Data;
import com.innominds.assignment.shipmentservice.thirdParty.Meta;
import com.innominds.assignment.shipmentservice.util.TestUtil;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootTest
public class ShipmentServiceTest {

    private static final String BASE_URL = "dummy";
    private static final String TRACKING_API = "tracking";
    private static final String API_KEY = "key";
    private static final String SLUG = "USPS";
    private static final String TRACKING_NUM = "1203";

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private TrackingServiceConfig trackingServiceConfig;
    @Mock
    private ObjectMapper mapper = new ObjectMapper();
    @InjectMocks
    private ShipmentService service = new ShipmentService();

    @BeforeEach
    public void setup(){
        Mockito.when(trackingServiceConfig.getBaseURL()).thenReturn(BASE_URL);
        Mockito.when(trackingServiceConfig.getTrackingAPI()).thenReturn(TRACKING_API);
        Mockito.when(trackingServiceConfig.getApiKey()).thenReturn(API_KEY);
    }

    @Test
    @DisplayName("Test get shipment method's success scenario")
    public void testGetShipment(){
        ResponseEntity<APIResponse> resEntity = new ResponseEntity(TestUtil.buildGetResponse(TRACKING_NUM,SLUG,"200"), HttpStatus.OK);

        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET),Mockito.any(HttpEntity.class),Mockito.eq(APIResponse.class))).thenReturn(resEntity);
        APIResponse res = service.getShipment(SLUG,TRACKING_NUM);
        Assertions.assertNotNull(res);
        Assertions.assertTrue(res.getMeta().getCode().equals("200"));
    }

    @Test
    @DisplayName("Test add shipment method's success scenario")
    public void testAddShipment(){
        ResponseEntity<APIResponse> resEntity = new ResponseEntity(TestUtil.buildGetResponse(TRACKING_NUM,SLUG,"201"), HttpStatus.OK);
        //ResponseEntity<APIResponse> res = template.postForEntity(path,entity,APIResponse.class);
        Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.any(HttpEntity.class),Mockito.eq(APIResponse.class))).thenReturn(resEntity);
        Shipment shipment = new Shipment("IN","IN",TRACKING_NUM,SLUG);
        CustomResponse res = service.addShipment(shipment);
        Assertions.assertNotNull(res);
        Assertions.assertTrue("OK".equals(res.getStatusCode()));
    }

    @Test
    @DisplayName("Test add shipment method when it gave some error")
    public void testAddShipmentWithFailedCase(){
        ResponseEntity<APIResponse> resEntity = new ResponseEntity(TestUtil.buildGetResponse(TRACKING_NUM,SLUG,"500"), HttpStatus.OK);
        //ResponseEntity<APIResponse> res = template.postForEntity(path,entity,APIResponse.class);
        Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.any(HttpEntity.class),Mockito.eq(APIResponse.class))).thenReturn(resEntity);
        Shipment shipment = new Shipment("IN","IN",TRACKING_NUM,SLUG);
        CustomResponse res = service.addShipment(shipment);
        Assertions.assertNotNull(res);
        Assertions.assertTrue("500".equals(res.getStatusCode()));
    }

    @Test
    @DisplayName("Test add shipment method when it gave some error on rest API call")
    public void testAddShipmentWithFailedCaseOnRestClientException(){

        Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.any(HttpEntity.class),Mockito.eq(APIResponse.class))).thenThrow(new RestClientException("DUMMY"));
        Shipment shipment = new Shipment("IN","IN",TRACKING_NUM,SLUG);
       // CustomResponse res = service.addShipment(shipment);
        SomeProcessingException exoectedException = Assertions.assertThrows(SomeProcessingException.class,()->service.addShipment(shipment),"something went wrong");
      Assertions.assertTrue(exoectedException.getMessage().contains("DUMMY"));
    }



}
