package com.pfernand.anticheat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public String originalFileChecksum() {
    return "1621562286";
  }
}
