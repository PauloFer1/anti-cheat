package com.pfernand.anticheat.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
  private final FileFinder fileFinder;
  private final FileChecksum fileChecksum;
  private final PasswordWriter passwordWriter;
  private final ServerPasswordReader serverPasswordReader;

  @Value("${app.executable.path}")
  private final String executablePath;
  @Value("${app.config.path}")
  private final String appConfigPath;

  @PostConstruct
  public void startServer() throws IOException, NoSuchAlgorithmException {
    log.info("Starting anti cheat");
    log.info("Executable file: {}", executablePath);
    fileFinder.findCheatFiles();
    fileChecksum.verifyExecFile(executablePath);
    final String gamePassword = serverPasswordReader.getPassword();
    passwordWriter.setPassword(appConfigPath, gamePassword);
    execExternal.exec(executablePath);
  }

}
