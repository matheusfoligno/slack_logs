package br.com.slacklogs.slack.logs.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.slacklogs.slack.logs.dto.SlackMessage;
import br.com.slacklogs.slack.logs.utils.SlackUtils;

@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(BadRequestException.class)
	public void handleErrorBadRequest(HttpServletRequest req, BadRequestException ex) {
		sendSlack(HttpStatus.BAD_REQUEST + " - " + ex.getMessage());
	}

	@ExceptionHandler(NotFoundException.class)
	public void handleErrorNotFoundException(HttpServletRequest req, NotFoundException ex) {
	}

	private void sendSlack(String message) {
		SlackMessage slackMessage = new SlackMessage(message);
		SlackUtils.sendMessage(slackMessage);
	}

}
