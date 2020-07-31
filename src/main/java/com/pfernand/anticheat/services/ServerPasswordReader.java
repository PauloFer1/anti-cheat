package com.pfernand.anticheat.services;

import com.pfernand.anticheat.exception.PasswordNotFoundException;
import com.pfernand.anticheat.exception.ServerConnectionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ServerPasswordReader {

  private static final String PASSWORD_VAR = "seta sv_privatePassword \"";

  @Value("${server.ip}")
  private final String serverIp;
  @Value("${server.ftp-port}")
  private final String serverFtpPort;
  @Value("${server.username}")
  private final String serverUsername;
  @Value("${server.password}")
  private final String serverPassword;
  @Value("${server.config-path}")
  private final String serverConfigPath;

  private final FTPClient ftpClient;

  public String getPassword() throws IOException {
    log.debug("Connecting...");
    ftpClient.connect(serverIp, Integer.parseInt(serverFtpPort));
    log.debug("Connected to server");
    ftpClient.login(serverUsername, serverPassword);
    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    ftpClient.enterLocalPassiveMode();

    try (BufferedReader bufferedReader =
        new BufferedReader(new InputStreamReader(ftpClient.retrieveFileStream(serverConfigPath)))) {
      String fileLine;
      while ((fileLine = bufferedReader.readLine()) != null) {
        if (fileLine.startsWith(PASSWORD_VAR)) {
          return fileLine.substring(PASSWORD_VAR.length(), fileLine.length() - 1);
        }
      }
    } finally {
      ftpClient.disconnect();
    }
    throw new PasswordNotFoundException(serverConfigPath);
  }
}
