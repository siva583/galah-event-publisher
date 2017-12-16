package com.intuit.galah.iface;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface IEventPublisherService {
	
	public Boolean publishMessages(String topicName, Object message) throws InterruptedException, ExecutionException, TimeoutException;
	
}
