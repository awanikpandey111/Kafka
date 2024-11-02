package com.awanik.kafka.orderProducer.customserializers;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class orderSerializer implements Serializer<Order> {

	@Override
	public byte[] serialize(String topic, Order order
			) {
		// TODO Auto-generated method stub
		byte[] response=null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			response= objectMapper.writeValueAsString(order).getBytes();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

}
