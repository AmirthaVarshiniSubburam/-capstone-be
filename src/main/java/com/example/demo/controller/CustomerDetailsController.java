package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.CustomerDetails;
import com.example.demo.model.JWTRequest;
import com.example.demo.model.JWTResponse;
import com.example.demo.service.CustomerDetailsService;
import com.example.demo.service.MyUserDetailsService;
import com.example.demo.util.JWTUtility;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
public class CustomerDetailsController {
	
	@Autowired
	CustomerDetailsService customerserv;
	
	@Autowired
	AuthenticationManager manager;
	
	@Autowired
	JWTUtility jwtUtitlity;
	
	@Autowired
	MyUserDetailsService userDetailService;
	
	@PostMapping("/customerdetails")
	public ResponseEntity<CustomerDetails> resgisterUser(@RequestBody CustomerDetails customerdetails) throws UserAlreadyExistException{
		return new ResponseEntity<CustomerDetails>(customerserv.registerUser(customerdetails),HttpStatus.CREATED);
	}
	
	@GetMapping("/customerdetails")
	public ResponseEntity<List<CustomerDetails>> getAllUser(){
		return new ResponseEntity<List<CustomerDetails>>(customerserv.getAllUser(), HttpStatus.OK);
	}
	
	@GetMapping("/customerdetails/{email}")
	public ResponseEntity<CustomerDetails> findAUserbyId(@PathVariable String email) throws UserNotFoundException{
		return new ResponseEntity<CustomerDetails>(customerserv.findUserbyemail(email), HttpStatus.OK);
	}
	
	@DeleteMapping("/customerdetails/{email}")
	public ResponseEntity<CustomerDetails> deleteAUserbyId(@PathVariable String email) throws UserNotFoundException{
		return new ResponseEntity<CustomerDetails>(customerserv.deleteAUser(email),HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/customerdetails")
	public ResponseEntity<CustomerDetails> updateAUser(@RequestBody CustomerDetails customerdetailsupdated) throws UserNotFoundException{
		return new ResponseEntity<CustomerDetails>(customerserv.updateAUser(customerdetailsupdated),HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public JWTResponse login(@RequestBody JWTRequest request) throws Exception {
		
		try {
			
			manager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())	
					);
			
		}
		catch( BadCredentialsException e ){
			throw new Exception("Wrong_Username_or_Password");
		}
		
		UserDetails userdetail = userDetailService.loadUserByUsername(request.getEmail());
		
		String generateToken = jwtUtitlity.generateToken(userdetail);
		return new JWTResponse(generateToken);
		
	}
	
}
