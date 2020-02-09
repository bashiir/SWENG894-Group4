package com.apptime.auth.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import com.apptime.auth.model.ResetPasswordRequest;
import com.apptime.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apptime.auth.model.ClientUser;
import com.apptime.auth.model.Roles;
import com.apptime.auth.model.Users;
import com.apptime.auth.repository.UserRepository;


@RestController
@CrossOrigin("http://localhost:8000")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<ClientUser> signup(@RequestBody Users user) {
		
		String pwd = user.getPassword();
		if(user.getRoles()== null) {
			Roles r = new Roles();
			r.setRole("USER");
			Set<Roles> rSet = new HashSet<Roles>();
			rSet.add(r);
			user.setRoles(rSet);
		}
		String encryptPwd = passwordEncoder.encode(pwd);
		user.setPassword(encryptPwd);
		userRepository.save(user);
		return buildSuccessfulResponse(user);
		
	}
	//https://stackoverflow.com/questions/3102819/disable-same-origin-policy-in-chrome
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/dashboard")
	public  ResponseEntity<ClientUser> login(Principal p) {	
		Users user = userRepository.findByUsername(p.getName());
		
		//return new ResponseEntity<ClientUser>(new ClientUser(user.getUsername(),user.getEmail()), HttpStatus.OK);

		return buildSuccessfulResponse(user);

	}

	@PostMapping("/resetPassword")
	public ResponseEntity<ClientUser> resetPassword(@RequestBody ResetPasswordRequest request, Principal p) {
		if (p == null) {
			return buildErrorResponse(HttpStatus.UNAUTHORIZED);
		}

		if (StringUtils.isEmpty(request.getUsername()) || StringUtils.isEmpty(request.getOldPassword()) || StringUtils.isEmpty(request.getNewPassword())) {
			return buildErrorResponse(HttpStatus.BAD_REQUEST);
		}
		if (!request.getUsername().equals(p.getName())) {
			return buildErrorResponse(HttpStatus.FORBIDDEN);
		}

		Users user = userRepository.findByUsername(request.getUsername());
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
