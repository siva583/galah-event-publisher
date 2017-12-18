package com.intuit.galah.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.galah.iface.IEventPublisherService;


@RestController
public class EventPublishController {

	@Autowired
	IEventPublisherService service;
	
	private static final Logger LOG = LoggerFactory.getLogger(EventPublishController.class);

	@PostMapping(value = "/publish/{topicName}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> publishToTopic(@Valid @PathVariable String topicName, @Valid @RequestBody Object message) {
		try {
			service.publishMessages(topicName, message);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
