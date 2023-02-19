package com.deguzman.DeGuzmanStuffAnywhere.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupConfigEvent {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppStartupConfigEvent.class);

	@EventListener(ApplicationReadyEvent.class)
	public void startApp() {

		LOGGER.info("AppStartupConfigEvent - startApp(): Application has started on port 8080");
	}
}