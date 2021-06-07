package br.com.slacklogs.slack.logs.service;

import org.springframework.stereotype.Service;

import br.com.slacklogs.slack.logs.exceptions.BadRequestException;

@Service
public class SlackTestService {

	public String get(String error) throws BadRequestException {
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
			throw new BadRequestException("Error bad request", e);
		}
	}
}
