package br.com.slacklogs.slack.logs.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.slacklogs.slack.logs.service.KafkaProducerService;

@ControllerAdvice
public class ExceptionHandlingController {

	@Autowired
	private KafkaProducerService kafkaProducer;

	@ExceptionHandler(BadRequestException.class)
	public void handleErrorBadRequest(HttpServletRequest req, BadRequestException ex) {
		sendKafkaProducer(HttpStatus.BAD_REQUEST + " - " + ex.getMessage());
	}

	@ExceptionHandler(NotFoundException.class)
	public void handleErrorNotFoundException(HttpServletRequest req, NotFoundException ex) {
	}

	private void sendKafkaProducer(String message) {
		kafkaProducer.sendMessage(message);
	}

}
