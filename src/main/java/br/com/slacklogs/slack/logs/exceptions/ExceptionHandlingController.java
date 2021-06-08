package br.com.slacklogs.slack.logs.exceptions;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;
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

		String message1 = new JSONObject().put("Data", formatter.format(new Date()))
				.put("Erro_status", HttpStatus.BAD_REQUEST).put("Usuario", "Matheus").put("Mensagem", ex.getMessage())
				.put("StackTrace", ExceptionUtils.getStackTrace(ex))
				.put("Sistema", "DataHub").toString();
		
		String message2 = new JSONObject().put("Data", formatter.format(new Date()))
				.put("Erro_status", HttpStatus.BAD_REQUEST).put("Usuario", "Matheus").put("Mensagem", ex.getMessage())
				.put("StackTrace", ExceptionUtils.getStackTrace(ex))
				.put("Sistema", "Python").toString();

		sendKafkaProducer(message1, message2);
	}

	@ExceptionHandler(NotFoundException.class)
	public void handleErrorNotFoundException(HttpServletRequest req, NotFoundException ex) {
	}

	private void sendKafkaProducer(String message, String messageOtherSystem) {
		kafkaProducer.sendMessage(message, messageOtherSystem);
	}

}
