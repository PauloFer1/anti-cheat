package com.pfernand.anticheat.config;

import com.pfernand.anticheat.services.ExecExternal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public ExecExternal execExternal() {
    return new ExecExternal();
  }
}
