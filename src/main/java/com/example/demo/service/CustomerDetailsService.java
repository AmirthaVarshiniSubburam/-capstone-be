package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.CustomerDetails;

public interface CustomerDetailsService {

	public CustomerDetails registerUser(CustomerDetails customerdetails) throws UserAlreadyExistException; //post request - save a user record
	public CustomerDetails findUserbyemail(String email) throws UserNotFoundException; // get request - retrieve a record
	public List<CustomerDetails> getAllUser(); //get request - to retrieve all records
	public CustomerDetails deleteAUser(String email) throws UserNotFoundException ; //delete request
	public CustomerDetails updateAUser(CustomerDetails customerdetails) throws UserNotFoundException ; // PUT - PATCH request

}
