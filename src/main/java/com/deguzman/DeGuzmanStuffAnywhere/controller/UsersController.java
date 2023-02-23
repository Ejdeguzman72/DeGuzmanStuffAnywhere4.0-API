package com.deguzman.DeGuzmanStuffAnywhere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deguzman.DeGuzmanStuffAnywhere.domain.UriConstants;
import com.deguzman.DeGuzmanStuffAnywhere.domain.UserListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.service.UserService;

@RestController
@CrossOrigin
public class UsersController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value = UriConstants.GET_ALL_USERS)
	public UserListResponse getAllUserInformation() {
		UserListResponse response = userService.findAllUsersInformation();
		return response;
	}
}
