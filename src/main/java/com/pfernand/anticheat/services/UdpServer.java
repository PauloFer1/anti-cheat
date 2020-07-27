package com.pfernand.anticheat.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UdpServer {

  private final UnicastSendingMessageHandler unicastSendingMessageHandler;

  public void handleMessage(Message message)
  {
    final Message toSendMessage = MessageBuilder.withPayload(message.getPayload()).build();
    log.debug("Sending [{}] to {} with data: {}",
        message.toString(),
        unicastSendingMessageHandler.getHost() + ":" + unicastSendingMessageHandler.getPort(),
        new String((byte []) message.getPayload())
    );
    log.debug("New Message headers: {}", toSendMessage.getHeaders().toString());
    unicastSendingMessageHandler.handleMessage(toSendMessage);
  }
}
