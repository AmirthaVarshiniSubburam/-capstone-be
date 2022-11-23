package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.LoanApp;
import com.example.demo.repository.LoanAppRepository;
import com.example.demo.service.LoanAppService;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
public class LoanAppController {
	
	@Autowired
	LoanAppService loanservice;
	
	@Autowired
	LoanAppRepository loanrepo;
	
	@PostMapping("/loanapplicationform")
	public ResponseEntity<LoanApp> resgisterUser(@RequestBody LoanApp loanapp) throws UserAlreadyExistException{
		return new ResponseEntity<LoanApp>(loanservice.registerUser(loanapp),HttpStatus.CREATED);
	}
	
	@GetMapping("/loanapplicationform")
	public ResponseEntity<List<LoanApp>> getAllUser(){
		return new ResponseEntity<List<LoanApp>>(loanservice.getAllUser(), HttpStatus.OK);
	}
	
	@GetMapping("/loanapplicationform/{loanid}")
	public ResponseEntity<LoanApp> findAUserbyId(@PathVariable Long loanid) throws UserNotFoundException{
		return new ResponseEntity<LoanApp>(loanservice.findUserbymobno(loanid), HttpStatus.OK);
	}
	
	@GetMapping("/loanapplicationform/mobno")
	public ResponseEntity<List<LoanApp>> findBymobno(@RequestParam Long mobno){
		return new ResponseEntity<List<LoanApp>>(loanrepo.findByMobno(mobno),HttpStatus.OK);
	}

}
