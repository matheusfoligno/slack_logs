package br.com.slacklogs.slack.logs.utils;

import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.slacklogs.slack.logs.dto.SlackMessage;

public class SlackUtils {

	private static final String URL = "https://slack.com/api/chat.postMessage";
	private static final String TOKEN = "xoxb-2144825941524-2162482801536-TpgnpwuODF1fdsLsYeRyo956";
	private static final String CHANNEL = "C023VS2M6BG";

	public static void sendMessage(SlackMessage message) {
		RestTemplate template = new RestTemplate();

		HttpHeaders headers = createHeaders();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL);
		HashMap<String, String> body = setBody(message);
		HttpEntity<?> entity = new HttpEntity<>(body, headers);

		template.exchange(builder.toUriString(), HttpMethod.POST, entity, Void.class);
	}

	private static HashMap<String, String> setBody(SlackMessage message) {
		HashMap<String, String> body = new HashMap<>();
		body.put("channel", CHANNEL);
		body.put("text", message.getText());
		return body;
	}

	private static HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + TOKEN);
		headers.add("Accept", "*/*");
		headers.add("Content-type", "application/json");
		return headers;
	}
}
