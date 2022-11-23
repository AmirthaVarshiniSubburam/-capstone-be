package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.LoanApp;


@Repository
public interface LoanAppRepository extends JpaRepository<LoanApp,Long> {
 List<LoanApp> findByMobno(Long mobno);
}
