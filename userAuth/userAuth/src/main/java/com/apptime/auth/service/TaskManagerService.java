package com.apptime.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apptime.auth.model.Task;
import com.apptime.auth.repository.TaskRepository;

@Service
public class TaskManagerService {
	@Autowired
	TaskRepository taskRepo;

	public List<Task> getTasks(String user) {
		
		List<Task> tasks =  taskRepo.findByUserName(user);
		
		return tasks;
	}



	public Task getTaskById(String user, int taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Task createTask(Task task, String user) {
		// TODO Auto-generated method stub
		task.setUserName(user);;
		taskRepo.save(task);
		return task;
	}

}
