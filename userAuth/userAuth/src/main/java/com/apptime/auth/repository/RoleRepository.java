package com.apptime.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apptime.auth.model.Roles;




public interface RoleRepository extends JpaRepository<Roles, Integer>{

}