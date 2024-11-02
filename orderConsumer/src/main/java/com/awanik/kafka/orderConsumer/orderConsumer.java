package com.awanik.kafka.orderConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.awanik.kafka.orderConsumer.customdeserializer.Order;
import com.awanik.kafka.orderConsumer.customdeserializer.OrderDeserializer;

public class orderConsumer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties props=new Properties();
		props.setProperty("bootstrap.servers","localhost:9092");
		props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");//can change as below
		props.setProperty("value.deserializer", OrderDeserializer.class.getName());
		props.setProperty("group.id", "OrderGroup");
		KafkaConsumer<String, Order> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Collections.singletonList("OrderCSTopic"));
		ConsumerRecords<String, Order> records = consumer.poll(Duration.ofSeconds(50));
		for(ConsumerRecord<String, Order> record:records) {
			String customerName = record.key();
			Order order=record.value();
			System.out.println("Consumer side");
			System.out.println("Customer Name "+customerName);
			System.out.println("Product "+order.getProduct());
			System.out.println("Quantity "+order.getQuantity());
		}
		consumer.close();
		
	}

}
