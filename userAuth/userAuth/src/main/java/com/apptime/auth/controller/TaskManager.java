package com.apptime.auth.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apptime.auth.model.ClientUser;
import com.apptime.auth.model.Task;
import com.apptime.auth.model.Users;
import com.apptime.auth.service.TaskManagerService;

@RestController
@RequestMapping(value = "/tasks")
public class TaskManager {
	@Autowired
	TaskManagerService taskService;
	@RequestMapping(value = "/")
	public ResponseEntity<List> getTasks(Principal p) throws ParseException{
		String user = p.getName();
		List<Task> tasks = taskService.getTasks(user);
        return new ResponseEntity<List>(tasks, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/task/{id}")
	public ResponseEntity<Task> getTask(@PathVariable("id") int taskId, Principal p) throws ParseException{
		String user = p.getName();
		Task tasks = taskService.getTaskById(user,taskId);
		//String sDate = "Thu, Dec 31 1998 23:37:50";  
		// SimpleDateFormat formatter5=new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");  
		// Date date=formatter5.parse(sDate);  
		//List<Task> tasks = Arrays.asList(new Task("clean",date,5));
        return new ResponseEntity<Task>(tasks, HttpStatus.OK);
	}
	
	@PostMapping("/task")
	public ResponseEntity<Task> signup(@RequestBody Task task, Principal p) {
		String user = p.getName();
		Task tasks = taskService.createTask(task,user);
        return new ResponseEntity<Task>(tasks, HttpStatus.OK);
	}

}