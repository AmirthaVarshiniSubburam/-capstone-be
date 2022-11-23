package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.CustomerDetails;
import com.example.demo.repository.CustomerDetailsRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	CustomerDetailsRepository CustomerRepo; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomerDetails customerdetails = CustomerRepo.findById(username).get();
		return new User(customerdetails.getEmail(), customerdetails.getPassword(), new ArrayList<>());

	}

}
