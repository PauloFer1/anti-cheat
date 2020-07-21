package com.pfernand.anticheat.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;

@Slf4j
public class UdpServer {

  public void handleMessage(Message message)
  {
    String data = new String((byte[]) message.getPayload());
    log.info(data);
  }
}
