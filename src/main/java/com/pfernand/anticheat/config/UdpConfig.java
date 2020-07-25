package com.pfernand.anticheat.config;

import com.pfernand.anticheat.services.UdpServer;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.expression.FunctionExpression;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.messaging.MessageChannel;

@Slf4j
@Configuration
public class UdpConfig {

  @Bean
  public UnicastReceivingChannelAdapter serverReceivingChannelAdapter(final MessageChannel requestChannel) {
    final UnicastReceivingChannelAdapter adapter = new UnicastReceivingChannelAdapter(57328);
    adapter.setOutputChannel(requestChannel);
    adapter.setLookupHost(false);
    return adapter;
  }

  @Bean
  public UnicastReceivingChannelAdapter gameReceivingChannelAdapter(final MessageChannel gameChannel) {
    final UnicastReceivingChannelAdapter adapter = new UnicastReceivingChannelAdapter(11111);
    adapter.setOutputChannel(gameChannel);
    adapter.setLookupHost(false);
    return adapter;
  }

  @Bean
  public IntegrationFlow gameReceivingFlow(
      final UdpServer udpServer,
      final UnicastReceivingChannelAdapter gameReceivingChannelAdapter) {
    return IntegrationFlows
        .from(gameReceivingChannelAdapter)
        .handle(udpServer, "handleMessage")
        .get();
  }

  @Bean
  public IntegrationFlow serverReceivingFlow(
      final UdpServer gameUdpServer,
      final UnicastReceivingChannelAdapter serverReceivingChannelAdapter
  ) {
    return IntegrationFlows
        .from(serverReceivingChannelAdapter)
        .handle(gameUdpServer, "handleMessage")
        .get();
  }

  @Bean
  public MessageChannel requestChannel() {
    return new DirectChannel();
  }

  @Bean
  public MessageChannel gameChannel() {
    return new DirectChannel();
  }

  @Bean
  @ServiceActivator(inputChannel = "requestChannel")
  public UnicastSendingMessageHandler unicastSendingMessageHandler(
      @Value("${server.ip}") final String serverIp,
      @Value("${server.port}") final int serverPort) {
    final UnicastSendingMessageHandler handler = new UnicastSendingMessageHandler(serverIp, serverPort);
    handler.setSocketExpressionString("@serverReceivingChannelAdapter.socket");
    return handler;
  }

  @Bean
  @ServiceActivator(inputChannel = "gameChannel")
  public UnicastSendingMessageHandler gameSendingMessageHandler() {
    final UnicastSendingMessageHandler handler = new UnicastSendingMessageHandler("127.0.0.1", 12203);
    handler.setSocketExpressionString("@gameReceivingChannelAdapter.socket");
    return handler;
  }

  @Bean
  public UdpServer gameUdpServer(final UnicastSendingMessageHandler gameSendingMessageHandler) {
    return new UdpServer(gameSendingMessageHandler);
  }
}
