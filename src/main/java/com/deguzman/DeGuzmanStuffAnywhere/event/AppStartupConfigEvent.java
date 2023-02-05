package com.deguzman.DeGuzmanStuffAnywhere.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.StartupScripts;

@Component
public class AppStartupConfigEvent {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppStartupConfigEvent.class);

	@Autowired
	private StartupScripts startupScripts;
	
	@EventListener(ApplicationReadyEvent.class)
	public void startApp() {

		LOGGER.info("AppStartupConfigEvent - startApp(): Application has started on port 8080");
	}
}