package com.deguzman.DeGuzmanStuffAnywhere.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deguzman.DeGuzmanStuffAnywhere.domain.UriConstants;

@RestController
@CrossOrigin
public class HapiApplicationController {

	@GetMapping(value = UriConstants.RUN_HAPI_APP)
	public ResponseEntity<String> runPythonScript() throws IOException {
		
		String command = "java -jar ./src/main/resources/HAPI-application/HAPI2.2.0.jar";
		
		Process p = Runtime.getRuntime().exec(command);
		String result;
		
		
		if (p.isAlive()) {
			result = "HAPI Applicatiopn has ran...";
		} else {
			result = "Failed to start HAPI application...";
		}
		return ResponseEntity.ok().body(result);
	}
}
