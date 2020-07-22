package com.pfernand.anticheat.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UdpClient {

  // 96.10.41.134:12243
  private final UnicastSendingMessageHandler unicastSendingMessageHandler;

  public void sendMessage(Object payload) {
    unicastSendingMessageHandler.handleMessage(MessageBuilder.withPayload(payload).build());
  }
}
