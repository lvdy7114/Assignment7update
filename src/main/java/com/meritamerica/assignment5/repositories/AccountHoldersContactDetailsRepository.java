package com.meritamerica.assignment5.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment5.models.AccountHoldersContactDetails;

public interface AccountHoldersContactDetailsRepository extends JpaRepository <AccountHoldersContactDetails, Integer>{
	
List<AccountHoldersContactDetails> findById(int id);

}
