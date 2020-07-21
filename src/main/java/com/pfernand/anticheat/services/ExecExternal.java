package com.pfernand.anticheat.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExecExternal {

  public void exec(final String path) throws IOException {
    log.info("Executing {}", path);
    final ProcessBuilder processBuilder = new ProcessBuilder(path);
    final LocalDateTime initProcessTime = LocalDateTime.now();
    LocalDateTime countTime = initProcessTime;
    final Process process = processBuilder.start();
    while (process.isAlive()) {
      final LocalDateTime now = LocalDateTime.now();
      if (countTime.plus(10, ChronoUnit.SECONDS).isBefore(now)) {
        log.info("Connected since {}", initProcessTime);
        countTime = now;
      }
    }
  }
}
