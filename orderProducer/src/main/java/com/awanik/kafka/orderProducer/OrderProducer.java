package com.awanik.kafka.orderProducer;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
public class OrderProducer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties props=new Properties();
		props.setProperty("bootstrap.servers","localhost:9092");
		props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.setProperty("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");

		KafkaProducer<String, Integer> producer = new KafkaProducer<String,Integer>(props);
		//ProducerRecord<String, Integer> record=new ProducerRecord<String, Integer>("OrderTopic", "mac book pro air",10);
		
		
		for(int i=0;i<5;i++) {
		try {
			ProducerRecord<String, Integer> record=new ProducerRecord<String, Integer>("OrderTopic", "mac book pro air",i++);
			producer.send(record,new OrderCallback());

		}catch(Exception e){
			e.printStackTrace();
		
		}
		
	}
			producer.close();


}
}
