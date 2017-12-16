package com.intuit.galah.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.google.gson.Gson;
import com.intuit.galah.iface.IEventPublisherService;

@Service
public class EventPublisherServiceImpl implements IEventPublisherService{
	
	private final static Logger log = Logger
			.getLogger(EventPublisherServiceImpl.class);

	@Autowired
	Gson gson = null;

	@Value("${zkServer}")
	private String zkClusterServers;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Override
	public Boolean publishMessages(String topicName, Object message )
			throws InterruptedException, ExecutionException, TimeoutException {
		String jsonMsg = gson.toJson(message);

		ListenableFuture<SendResult<String, String>> future = kafkaTemplate
				.send(topicName, jsonMsg);

		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				log.info("Message published successfully:- " + result.getProducerRecord());
			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("Message publish failure." + ex.getMessage());
			}
		});

		future.get(20, TimeUnit.SECONDS);
		return true;
	}

}
