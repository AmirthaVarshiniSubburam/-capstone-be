package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.CustomerDetails;
import com.example.demo.repository.CustomerDetailsRepository;
import com.example.demo.service.CustomerDetailsServiceImpl;


@ExtendWith(MockitoExtension.class)
public class CustomerDetailsServiceTest {
	
	@Mock
    private CustomerDetailsRepository customerrepo;
	
	@InjectMocks
    private CustomerDetailsServiceImpl  customerservice;
    private CustomerDetails customers,customers1;
    private List<CustomerDetails> customerlist;
    private Optional optional;

    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        customers = new CustomerDetails("amirtha@gmail.com", "ammu", "Hari123", null, null, "Amirtha");
        customers1 = new CustomerDetails("amirtha@gmail.com", "ammu","Hari12345",null, null, "Amirtha");
        optional = Optional.of(customers);
    }


    @AfterEach
    public void tearDown() {
    	customers = null;
    }

    @Test
    public void givenCustomerDetailsToSaveThenShouldReturnSavedCustomerDetails() throws UserAlreadyExistException {
        when(customerrepo.save(any())).thenReturn(customers);
        assertEquals(customers, customerservice.registerUser(customers));
        verify(customerrepo, times(1)).save(any());
    }

    @Test
    public void givenCustomerDetailsToSaveThenShouldNotReturnSavedCustomerDetails() {
        when(customerrepo.save(any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class,() -> {
        	customerservice.registerUser(customers);
        });
        verify(customerrepo, times(1)).save(any());
    }

    @Test
    public void givenGetAllCustomerDetailsThenShouldReturnListOfAllCustomerDetails() {
    	customerrepo.save(customers);
        //stubbing the mock to return specific data
        when(customerrepo.findAll()).thenReturn(customerlist);
        List<CustomerDetails> walletUserList1 = customerservice.getAllUser();
        assertEquals(customerlist, walletUserList1);
        verify(customerrepo, times(1)).save(customers);
        verify(customerrepo, times(1)).findAll();
    }

    @Test
    public void givenWalletUserIdThenShouldReturnRespectiveCustomerDetails() throws UserNotFoundException {
        when(customerrepo.findById(anyString())).thenReturn(Optional.of(customers));
        CustomerDetails retrievedwalletUser = customerservice.findUserbyemail(customers.getEmail());
        verify(customerrepo, times(1)).findById(anyString());

    }

   
    @Test
    void givenWalletUserIdToDeleteThenShouldReturnDeletedCustomerDetails() throws UserNotFoundException {
        when(customerrepo.findById(customers.getEmail())).thenReturn(optional);
        CustomerDetails deletedWalletUser = customerservice.deleteAUser("Hari@gmail.com");
        assertEquals("Hari@gmail.com", deletedWalletUser.getEmail());

        verify(customerrepo, times(2)).findById(customers.getEmail());
        verify(customerrepo, times(1)).deleteById(customers.getEmail());
    }

    @Test
    void givenWalletUserIdToDeleteThenShouldNotReturnDeletedCustomerDetails() throws UserNotFoundException {
        when(customerrepo.findById(customers.getEmail())).thenReturn(Optional.empty());
        CustomerDetails deletedWalletUser = customerservice.deleteAUser("Hari@gmail.com");
        verify(customerrepo, times(1)).findById(customers.getEmail());
    }
    
    @Test
    public void givenWalletUserToUpdateThenShouldReturnUpdatedCustomerDetails() throws UserNotFoundException {
        when(customerrepo.findById(customers.getEmail())).thenReturn(optional);
        when(customerrepo.save(customers)).thenReturn(customers);
        customers.setEmail("Hari@gmail.com");
        CustomerDetails walletUsers1 = customerservice.updateAUser(customers);
        assertEquals(walletUsers1.getEmail(), "Hari@gmail.com");
        verify(customerrepo, times(1)).save(customers);
        verify(customerrepo, times(2)).findById(customers.getEmail());
    }

    @Test
    public void givenWalletUserToUpdateThenShouldNotReturnUpdatedCustomerDetails() throws UserNotFoundException {
        when(customerrepo.findById(customers.getEmail())).thenReturn(Optional.empty());
        CustomerDetails walletUsers1 = customerservice.updateAUser(customers);
        assertNull(walletUsers1);
        verify(customerrepo, times(1)).findById(customers.getEmail());
    }


}