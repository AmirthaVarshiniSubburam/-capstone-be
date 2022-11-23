package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.exception.UserNotFoundException;

import com.example.demo.model.LoanApp;

public interface LoanAppService {
	
	public LoanApp registerUser(LoanApp loanapp) throws UserAlreadyExistException; //post request - save a user record
	public LoanApp findUserbymobno(Long mobno) throws UserNotFoundException; // get request - retrieve a record
	public List<LoanApp> getAllUser(); //get request - to retrieve all records
	public LoanApp deleteAUser(Long mobno) throws UserNotFoundException ; //delete request
	public LoanApp updateAUser(LoanApp loanapp) throws UserNotFoundException ; // PUT - PATCH request

}
