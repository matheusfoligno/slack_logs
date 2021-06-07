package br.com.slacklogs.slack.logs.exceptions;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
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
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

		StringBuilder message = new StringBuilder();
		message.append("*Data: " + formatter.format(new Date()) + " / Erro status: " + HttpStatus.BAD_REQUEST + "*\n");
		message.append("Usuário: Matheus \n");
		message.append("Mensagem: " + ex.getMessage() + "\n");
		message.append("StackTrace: " + ExceptionUtils.getStackTrace(ex) + "\n");

		sendKafkaProducer(message.toString());
	}

	@ExceptionHandler(NotFoundException.class)
	public void handleErrorNotFoundException(HttpServletRequest req, NotFoundException ex) {
	}

	private void sendKafkaProducer(String message) {
		kafkaProducer.sendMessage(message);
	}

}
