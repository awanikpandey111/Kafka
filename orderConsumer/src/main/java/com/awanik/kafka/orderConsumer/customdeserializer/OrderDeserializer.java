package com.awanik.kafka.orderConsumer.customdeserializer;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderDeserializer implements Deserializer<Order> {

	@Override
	public Order deserialize(String topic, byte[] data) {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();
		Order order=null;
		try {
		order=objectMapper.readValue(data,Order.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

}
