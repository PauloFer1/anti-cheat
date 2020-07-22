package com.pfernand.anticheat.services;

import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppManager {

  private final ExecExternal execExternal;

  @Value("${app.executable.path}")
  private String executablePath;

  @PostConstruct
  public void startServer() throws IOException {
    log.info("Starting anti cheat");
    log.info("Executable file: {}", executablePath);
    execExternal.exec(executablePath);
  }

}
