package com.pfernand.anticheat.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UdpServer {

  private final UdpClient udpClient;

  public void handleMessage(Message message)
  {
    String data = new String((byte[]) message.getPayload());
    log.info(data);
    udpClient.sendMessage(message.getPayload());
  }
}
