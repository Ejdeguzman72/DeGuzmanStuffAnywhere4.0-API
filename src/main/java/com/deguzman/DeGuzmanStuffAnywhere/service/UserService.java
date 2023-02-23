package com.deguzman.DeGuzmanStuffAnywhere.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deguzman.DeGuzmanStuffAnywhere.authentication_models.User;
import com.deguzman.DeGuzmanStuffAnywhere.authentication_repository.UserRepository;
import com.deguzman.DeGuzmanStuffAnywhere.domain.UserListResponse;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserListResponse findAllUsersInformation() {
		UserListResponse response = new UserListResponse();
		List<User> list = userRepository.findAll();
		
		response.setList(list);
		return response;
	}
}
