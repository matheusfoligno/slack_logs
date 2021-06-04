package br.com.slacklogs.slack.logs.service;

import org.springframework.stereotype.Service;

import br.com.slacklogs.slack.logs.exceptions.BadRequestException;
import br.com.slacklogs.slack.logs.exceptions.NotFoundException;

@Service
public class SlackTestService {

	public String get(String error) throws BadRequestException, NotFoundException {
		switch (error) {
		case "BadRequest":
			throw new BadRequestException("Error bad request");
		case "NotFound":
			throw new NotFoundException("Error not found");
		default:
			return "Teste";
		}
	}
}
