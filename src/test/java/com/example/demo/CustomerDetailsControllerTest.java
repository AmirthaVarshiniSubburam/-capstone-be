package com.example.demo;


import com.example.demo.controller.CustomerDetailsController;
import com.example.demo.model.CustomerDetails;
import com.example.demo.service.CustomerDetailsService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CustomerDetailsControllerTest {

    private MockMvc mockMvc;
    @Mock
    CustomerDetailsService customerservice;
    @InjectMocks
    private CustomerDetailsController customercontroller;

    private CustomerDetails customerdetails;
    private List<CustomerDetails> customerdetailsList;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customercontroller).build();
        customerdetails = new CustomerDetails();
        customerdetails.setEmail("amirtha@gmail.com");
        customerdetails.setFirstname("amirtha");
        customerdetails.setLastname("varshini");
        customerdetails.setAccountNo((long)7654);
        customerdetails.setMobileNo((long)876543);
        customerdetails.setPassword("Amitha@123");
        customerdetailsList = new ArrayList<>();
    	customerdetailsList.add(customerdetails);
    }

    @AfterEach
    public void tearDown() {
    	customerdetails = null;
    }

    @Test
    public void givenWalletUserToSaveThenShouldReturnSavedWalletUser() throws Exception {
        when(customerservice.registerUser(any())).thenReturn(customerdetails);
        mockMvc.perform(post("/customerdetails")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerdetails)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(customerservice).registerUser(any());
    }

    @Test
    public void givenGetAllWalletUsersThenShouldReturnListOfAllWalletUsers() throws Exception {
        when(customerservice.getAllUser()).thenReturn(customerdetailsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/customerdetails")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(customerdetails)))
                .andDo(MockMvcResultHandlers.print());
        verify(customerservice).getAllUser();
        verify(customerservice, times(1)).getAllUser();

    }

    @Test
    void givenWalletUserIdThenShouldReturnRespectiveWalletUser() throws Exception {
        when(customerservice.findUserbyemail(customerdetails.getEmail())).thenReturn(customerdetails);
        mockMvc.perform(get("/customerdetails/amirtha@gmail.com"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void givenWalletUserIdToDeleteThenShouldNotReturnDeletedWalletUser() throws Exception {
        when(customerservice.deleteAUser(customerdetails.getEmail())).thenReturn(customerdetails);
        mockMvc.perform(delete("/customerdetails/amirtha@gmail.com")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerdetails)))
                .andExpect(MockMvcResultMatchers.status().isAccepted()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenWalletUserToUpdateThenShouldReturnUpdatedWalletUser() throws Exception {
        when(customerservice.updateAUser(any())).thenReturn(customerdetails);
        mockMvc.perform(put("/customerdetails").contentType(MediaType.APPLICATION_JSON).content(asJsonString(customerdetails)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}