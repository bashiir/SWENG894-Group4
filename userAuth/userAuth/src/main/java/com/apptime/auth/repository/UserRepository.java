package com.apptime.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.apptime.auth.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{
	Users findByUsername(String username);
}
