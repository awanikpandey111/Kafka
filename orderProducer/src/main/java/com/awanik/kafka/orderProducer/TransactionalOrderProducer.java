package com.awanik.kafka.orderProducer;


import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.awanik.kafka.orderProducer.customserializers.Order;
import com.awanik.kafka.orderProducer.customserializers.partitioners.VIPPartitioner;
public class TransactionalOrderProducer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties props=new Properties();
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerSerializer");
		props.setProperty(ProducerConfig.TRANSACTIONAL_ID_CONFIG,"order-producer-1");
		//props.setProperty(ProducerConfig.MAX_BLOCK_MS_CONFIG, "1000");
		
		KafkaProducer<String, Integer> producer = new KafkaProducer<String,Integer>(props);
		producer.initTransactions();
		ProducerRecord<String, Integer> record=new ProducerRecord<String, Integer>("OrderTopic","MacBookPro",10);
		ProducerRecord<String, Integer> record2=new ProducerRecord<String, Integer>("OrderTopic","Iphone",10);

		
		try {
			producer.beginTransaction();
			producer.send(record);
			producer.send(record2);
			producer.commitTransaction();

		}catch(Exception e){
			producer.abortTransaction();
			e.printStackTrace();
		
		}
		
			producer.close();


}
}
