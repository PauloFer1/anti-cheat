package com.pfernand.anticheat.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestUdpServer {

  public void handleMessage(Message message)
  {
    String data = new String((byte[]) message.getPayload());
    log.info("Test Game server receiving: {}", data);
  }
}
