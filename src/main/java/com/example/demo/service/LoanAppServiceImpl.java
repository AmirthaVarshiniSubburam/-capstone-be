package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.LoanApp;
import com.example.demo.repository.LoanAppRepository;

@Service
public class LoanAppServiceImpl implements LoanAppService{
	
	@Autowired
	LoanAppRepository Loanrepo;
	
	@Override
	public LoanApp registerUser(LoanApp loanapp) throws UserAlreadyExistException {
		// TODO Auto-generated method stub
		return Loanrepo.save(loanapp);
	}

	@Override
	public LoanApp findUserbymobno(Long mobno) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return Loanrepo.findById(mobno).get();
	}

	@Override
	public List<LoanApp> getAllUser() {
		// TODO Auto-generated method stub
		return Loanrepo.findAll();
	}

	@Override
	public LoanApp deleteAUser(Long mobno) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoanApp updateAUser(LoanApp loanapp) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
