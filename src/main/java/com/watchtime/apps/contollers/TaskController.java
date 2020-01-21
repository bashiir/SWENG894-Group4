package com.watchtime.apps.contollers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.watchtime.apps.models.Task;
import com.watchtime.apps.services.TaskService;

@RestController
public class TaskController {
	@Autowired
	TaskService taskService;
	@RequestMapping(value = "/users/{id}")
	public ResponseEntity<List> listTasks(@PathVariable("id") int id) throws ParseException{
		String sDate = "Thu, Dec 31 1998 23:37:50";  
		 SimpleDateFormat formatter5=new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");  
		 Date date=formatter5.parse(sDate);  
		
  List<Task> tasks = Arrays.asList(new Task("clean",date,5));
        return new ResponseEntity<List>(tasks, HttpStatus.OK);
	}

}
