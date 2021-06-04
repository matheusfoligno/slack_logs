package br.com.slacklogs.slack.logs.utils;

import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.slacklogs.slack.logs.dto.SlackMessage;

public class SlackUtils {

	private static final String URL = "aHR0cHM6Ly9zbGFjay5jb20vYXBpL2NoYXQucG9zdE1lc3NhZ2U=";
	private static final String TOKEN = "eG94Yi0yMTQ0ODI1OTQxNTI0LTIxNjI0ODI4MDE1MzYtdDZCa3dmOWt6Y3laT0daZ24xU3I1V2RF";
	private static final String CHANNEL = "QzAyM1ZTMk02Qkc=";

	public static void sendMessage(SlackMessage message) {
		RestTemplate template = new RestTemplate();

		HttpHeaders headers = createHeaders();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(new String(Base64.decodeBase64(URL.getBytes())));
		HashMap<String, String> body = setBody(message);
		HttpEntity<?> entity = new HttpEntity<>(body, headers);

		template.exchange(builder.toUriString(), HttpMethod.POST, entity, Void.class);
	}

	private static HashMap<String, String> setBody(SlackMessage message) {
		HashMap<String, String> body = new HashMap<>();
		body.put("channel", new String(Base64.decodeBase64(CHANNEL.getBytes())));
		body.put("text", message.getText());
		return body;
	}

	private static HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders();
		
		String token = new String(Base64.decodeBase64(TOKEN.getBytes()));
		
		headers.add("Authorization", "Bearer " + token);
		headers.add("Accept", "*/*");
		headers.add("Content-type", "application/json");
		return headers;
	}
}
