package com.apptime.auth.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.apptime.auth.exception.UserExistingException;
import com.apptime.auth.model.ResetPasswordRequest;
import com.apptime.auth.service.SecurityService;
import com.apptime.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.apptime.auth.model.ClientUser;
import com.apptime.auth.model.Roles;
import com.apptime.auth.model.Users;
import com.apptime.auth.repository.UserRepository;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private SecurityService securityService;

	@PostMapping("/signup")
	public ResponseEntity<ClientUser> signup(@RequestBody Users user) {
		String password = user.getPassword();
		if(user.getRoles()== null) {
			Roles r = new Roles();
			r.setRole("USER");
			Set<Roles> rSet = new HashSet<Roles>();
			rSet.add(r);
			user.setRoles(rSet);
		}
		try {
			userService.createUser(user);
		} catch (UserExistingException e) {
			return buildErrorResponse(HttpStatus.CONFLICT);
		}
		securityService.autoLogin(user.getUsername(), password);

		return buildSuccessfulResponse(user);
		
	}
	//https://stackoverflow.com/questions/3102819/disable-same-origin-policy-in-chrome
	//@PreAuthorize("hasAnyRole('ADMIN')")
	//@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/dashboard")
	public  ResponseEntity<ClientUser> dashboard(Principal p) {
		Users user = userService.findByUsername(p.getName());
		
		if (user != null) {
			return buildSuccessfulResponse(user);
		} else {
			return buildErrorResponse(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<ClientUser> login(@RequestBody Users user) {
		Users userInDb = userService.findByUsername(user.getUsername());
		if (userInDb != null && passwordEncoder.matches(user.getPassword(), userInDb.getPassword())) {
			securityService.autoLogin(user.getUsername(), user.getPassword());
			return buildSuccessfulResponse(userInDb);
		}
		return buildErrorResponse(HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		System.err.println("logined: " + securityService.findLoggedInUsername());
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@PostMapping("/resetPassword")
	public ResponseEntity<ClientUser> resetPassword(@RequestBody ResetPasswordRequest request) {
		if (StringUtils.isEmpty(request.getUsername()) || StringUtils.isEmpty(request.getOldPassword()) || StringUtils.isEmpty(request.getNewPassword())) {
			return buildErrorResponse(HttpStatus.BAD_REQUEST);
		}
		Users user = userService.findByUsername(request.getUsername());
		if (user == null) {
			// cannot find the user
			return buildErrorResponse(HttpStatus.NOT_FOUND);
		}
		boolean result = userService.resetPassword(request.getUsername(), request.getOldPassword(), request.getNewPassword());
		if (result) {
			// password is updated
			return buildSuccessfulResponse(user);
		} else {
			// old password doesn't match the record
			return buildErrorResponse(HttpStatus.UNAUTHORIZED);
		}
	}

	private ClientUser parseUser(Users user) {
		return new ClientUser(user.getUsername(),user.getEmail());
	}

	private ResponseEntity<ClientUser> buildSuccessfulResponse(Users user) {
		return new ResponseEntity<>(parseUser(user), HttpStatus.OK);
	}

	private ResponseEntity<ClientUser> buildErrorResponse(HttpStatus httpStatus) {
		return new ResponseEntity<>(httpStatus);
	}
}
