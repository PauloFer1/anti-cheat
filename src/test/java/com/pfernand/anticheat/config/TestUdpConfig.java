package com.pfernand.anticheat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;

@TestConfiguration
public class TestUdpConfig {

  @Bean
  public IntegrationFlow testGameReceivingFlow(final TestUdpServer testUdpServer, @Value("${server.port}") final int serverPort) {
    return IntegrationFlows
        .from(new UnicastReceivingChannelAdapter(serverPort))
        .handle(testUdpServer, "handleMessage")
        .get();
  }
}
