package com.pfernand.anticheat.config;

import com.pfernand.anticheat.services.UdpServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;

@Slf4j
@Configuration
public class UdpConfig {

  @Bean
  public UdpServer udpServer() {
    log.info("Creating UDP proxy server");
    return new UdpServer();
  }

  @Bean
  public IntegrationFlow processUniCastUdpMessage(final UdpServer udpServer) {
    return IntegrationFlows
        .from(new UnicastReceivingChannelAdapter(11111))
        .handle(udpServer, "handleMessage")
        .get();
  }

}
