package com.awanik.kafka.orderProducer;


import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.awanik.kafka.orderProducer.customserializers.Order;
import com.awanik.kafka.orderProducer.customserializers.partitioners.VIPPartitioner;
public class OrderProducer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties props=new Properties();
		props.setProperty("bootstrap.servers","localhost:9092");
		props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.setProperty("value.serializer", "com.awanik.kafka.orderProducer.customserializers.orderSerializer");
		props.setProperty("partitioner.class", VIPPartitioner.class.getName());

		KafkaProducer<String, Order> producer = new KafkaProducer<String,Order>(props);
		Order order = new Order();
		order.setCustomerName("sxsxsxsx");
		order.setProduct("Phone");
		order.setQuantity(10);
		ProducerRecord<String, Order> record=new ProducerRecord<String, Order>("OrderPartitionedTopic", order.getCustomerName(),order);
		
		
		try {
			producer.send(record);

		}catch(Exception e){
			e.printStackTrace();
		
		}
		
			producer.close();


}
}
