package br.com.slacklogs.slack.logs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.slacklogs.slack.logs.exceptions.BadRequestException;

@Service
public class SlackTestService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SlackTestService.class);

	public String get(String error) throws BadRequestException {
		LOGGER.info("Entrou método");
		
		try {
			switch (error) {
			case "BadRequest":
				int a = 0;
				int b = 10;
				int result = b / a;
				System.out.println(result);
			default:
				return "Teste";
			}
		} catch (Exception e) {
			LOGGER.info("Saiu método");
			throw new BadRequestException("Error bad request", e);
		}
	}
}
