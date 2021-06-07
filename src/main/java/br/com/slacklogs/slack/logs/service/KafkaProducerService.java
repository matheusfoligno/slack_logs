package br.com.slacklogs.slack.logs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

	private static final String TOPIC = "slack";
	private static final String TOPIC_TESTE = "slack_test";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String message) {
		this.kafkaTemplate.send(TOPIC, message);
		this.kafkaTemplate.send(TOPIC_TESTE, "Teste");
	}

}
