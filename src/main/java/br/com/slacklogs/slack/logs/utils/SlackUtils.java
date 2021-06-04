package br.com.slacklogs.slack.logs.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.slacklogs.slack.logs.dto.SlackMessage;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class SlackUtils {
	private static String slackWebhookUrl = "https://hooks.slack.com/services/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";

	public static void sendMessage(SlackMessage message) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(slackWebhookUrl);

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(message);

			StringEntity entity = new StringEntity(json);
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			client.execute(httpPost);
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
