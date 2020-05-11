package com.meritamerica.assignment5.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment5.models.AccountHolder;



//Repository for account holder in particular
//Spring Data Jpa enforces a repository for particular model class
public interface AccountHolderRepository extends JpaRepository <AccountHolder, Integer>{
		
	List<AccountHolder> findByName(String name);

}

