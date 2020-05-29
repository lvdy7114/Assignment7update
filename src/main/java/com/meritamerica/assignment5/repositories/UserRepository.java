package com.meritamerica.assignment5.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment5.models.User;

public interface UserRepository extends JpaRepository<User , Integer> {
	Optional<User> FindByUserName(String userName);

}
