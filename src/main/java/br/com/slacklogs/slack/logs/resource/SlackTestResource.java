package br.com.slacklogs.slack.logs.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.slacklogs.slack.logs.exceptions.BadRequestException;
import br.com.slacklogs.slack.logs.exceptions.NotFoundException;
import br.com.slacklogs.slack.logs.service.SlackTestService;

@RestController
@RequestMapping(value = "/slack")
public class SlackTestResource {

	@Autowired
	private SlackTestService service;

	@GetMapping
	public ResponseEntity<String> get(@RequestParam String error) throws BadRequestException, NotFoundException {
		return ResponseEntity.ok(service.get(error));
	}

}
