package com.apptime.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//only admin can have access to this
@RequestMapping("/rest/auth/")
public class AdminController {
	
	@GetMapping("/timesheet")
	public String timesheet() {
		return "admin usercases";
	}

}
