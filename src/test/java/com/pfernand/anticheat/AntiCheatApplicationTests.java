package com.pfernand.anticheat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootTest
class AntiCheatApplicationTests {

	@Test
	void contextLoads() {
		UnicastSendingMessageHandler handler =
				new UnicastSendingMessageHandler("localhost", 11111);

		String payload = "Hello world";
		handler.handleMessage(MessageBuilder.withPayload(payload).build());
	}

}
