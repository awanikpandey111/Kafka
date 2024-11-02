package com.awanik.kafka.orderProducer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class OrderCallback implements Callback {

	@Override
	public void onCompletion(RecordMetadata metadata, Exception exception) {
		// TODO Auto-generated method stub
		System.out.println(metadata.partition());
		System.out.println(metadata.offset());
		System.out.println("message sent successfully");
		if(exception!=null) {
			exception.printStackTrace();
		}

	}

}
