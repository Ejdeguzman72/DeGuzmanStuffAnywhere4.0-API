package com.deguzman.DeGuzmanStuffAnywhere.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.HomeInfoDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.UriConstants;
import com.deguzman.DeGuzmanStuffAnywhere.model.HomeInfo;

@RestController
@CrossOrigin
public class HomeInfoController {

	@Autowired
	private HomeInfoDaoImpl homeInfoDaoImpl;

	@GetMapping(value = UriConstants.GET_HOME_INFORMATION)
	@CrossOrigin
	public List<HomeInfo> getAllHomeInformation() {
		return homeInfoDaoImpl.getAllHomeInfo();
	}
}
