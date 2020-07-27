package com.pfernand.anticheat.services;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExecExternal {

  public void exec(final String path) throws IOException {

    Runnable executableRunnable = () -> {
      log.info("Executing {}", path);
      final ProcessBuilder processBuilder = new ProcessBuilder();
      processBuilder.command("open", path);
      Process process;
      try {
        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
          process = processBuilder.start();
        } else {
          process = Runtime.getRuntime().exec(path);
        }
      } catch (IOException e) {
        log.error("Error executing file: {}", path, e);
        throw new RuntimeException(e);
      }
      try {
        int exitCode = process.waitFor();
        log.info("exit code: {}", exitCode);
      } catch (InterruptedException e) {
        log.error("Error waiting for process: ", e);
        Thread.currentThread().interrupt();
      }
    };

    final Thread executableTask = new Thread(executableRunnable);
    executableTask.start();
  }
}
