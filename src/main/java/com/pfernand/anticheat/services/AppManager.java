package com.pfernand.anticheat.services;

import java.io.IOException;
import java.net.URL;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppManager {

  private ExecExternal execExternal;

  @PostConstruct
  public void startServer() throws IOException {
    log.info("Starting anti cheat");
    final URL urlToFile = AppManager.class.getClassLoader().getResource("Chess.app");
    log.info("Resource file: {}", urlToFile);
    String filePath = "";
    try {
      filePath = urlToFile.getPath();
    } catch (NullPointerException ex) {
      log.error("File does not exist", ex);
      return;
    }
    log.info("Executing file: {}", filePath);
    execExternal.exec(filePath);
  }

}
