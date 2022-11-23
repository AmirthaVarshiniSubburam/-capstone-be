package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.CustomerDetails;

import com.example.demo.repository.CustomerDetailsRepository;


@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService  {
	
	@Autowired
	CustomerDetailsRepository CustomerRepo;

	@Override
	public CustomerDetails registerUser(CustomerDetails customerdetails) throws UserAlreadyExistException {
		// TODO Auto-generated method stub
		return CustomerRepo.save(customerdetails);
	}

	@Override
	public CustomerDetails findUserbyemail(String email) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return CustomerRepo.findById(email).get();
	}

	@Override
	public List<CustomerDetails> getAllUser() {
		// TODO Auto-generated method stub
		return CustomerRepo.findAll();
	}

	@Override
	public CustomerDetails deleteAUser(String email) throws UserNotFoundException {
		CustomerDetails customerdetailsdeleted = null;
		Optional optional = CustomerRepo.findById(email);
		if (optional.isPresent()) {
			customerdetailsdeleted = CustomerRepo.findById(email).get();
			CustomerRepo.deleteById(email);
		}
		return customerdetailsdeleted;

	}

	@Override
	public CustomerDetails updateAUser(CustomerDetails customerdetails) throws UserNotFoundException {
		CustomerDetails customerdetailsupdated = null;
		
		Optional optional = CustomerRepo.findById(customerdetails.getEmail());
		
		if (optional.isPresent()) {
			CustomerDetails getUser = CustomerRepo.findById(customerdetails.getEmail()).get();
			getUser.setEmail(customerdetails.getEmail());
			getUser.setMobileNo(customerdetails.getMobileNo());
			getUser.setFirstname(customerdetails.getFirstname());
			getUser.setLastname(customerdetails.getLastname());
			getUser.setPassword(customerdetails.getPassword());
			getUser.setAccountNo(customerdetails.getAccountNo());
			
			customerdetailsupdated = CustomerRepo.save(getUser);
		}
		
		return customerdetailsupdated;
	}

}
