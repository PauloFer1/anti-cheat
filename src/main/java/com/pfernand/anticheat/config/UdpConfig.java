package com.pfernand.anticheat.config;

import com.pfernand.anticheat.services.UdpServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;

@Slf4j
@Configuration
public class UdpConfig {

  @Bean
  public IntegrationFlow gameReceivingFlow(final UdpServer udpServer) {
    return IntegrationFlows
        .from(new UnicastReceivingChannelAdapter(11111))
        .handle(udpServer, "handleMessage")
        .get();
  }

  @Bean
  public UnicastSendingMessageHandler unicastSendingMessageHandler(@Value("${server.ip}") final String serverIp, @Value("${server.port}") final int serverPort) {
    return new UnicastSendingMessageHandler(serverIp, serverPort);
  }

}
