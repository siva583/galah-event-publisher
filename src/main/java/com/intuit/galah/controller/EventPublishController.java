package com.intuit.galah.controller;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.galah.iface.IEventPublisherService;
import com.intuit.galah.model.User;


@RestController
public class EventPublishController {
	
	@Autowired
	IEventPublisherService service;
	
	@GetMapping(value = "/test", produces = {MediaType.TEXT_PLAIN_VALUE})
	public String test(@RequestBody User user) {
		try {
			service.publishMessages("test", "Hi from Galah!");
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Success";
	}
	
	@PostMapping(value = "/publish/{topicName}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> publishToTopic(@Valid @PathVariable String topicName, @Valid @RequestBody Object message) {
		//validate
		//send - success 
		//send - fault
		//excption handling - ControllerAdvice
		//unit test cases
		try {
			service.publishMessages(topicName, message);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>("Success", HttpStatus.OK);
		
	}

}
