package com.pfernand.anticheat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
class AntiCheatApplicationTests {

	@Test
	void contextLoads() {
		UnicastSendingMessageHandler game =
				new UnicastSendingMessageHandler("localhost", 11111);
		String challenge = "get challenge";
		game.handleMessage(MessageBuilder.withPayload(challenge).build());

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String challengeResponse = "challenge response";
		UnicastSendingMessageHandler server =
				new UnicastSendingMessageHandler("localhost", 57328);
		server.handleMessage(MessageBuilder.withPayload(challengeResponse).build());

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String connect = "connect";
		game.handleMessage(MessageBuilder.withPayload(connect).build());

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String connectResponse = "connect response";
		server.handleMessage(MessageBuilder.withPayload(connectResponse).build());
	}

}
