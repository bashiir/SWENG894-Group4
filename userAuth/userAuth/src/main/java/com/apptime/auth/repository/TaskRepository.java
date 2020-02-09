package com.apptime.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apptime.auth.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{
	
	
	 List<Task> findByUserName(String user);

}
