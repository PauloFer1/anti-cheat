package com.pfernand.anticheat.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PasswordWriter {

  private static final String PASSWORD_VAR = "seta password ";

  public void setPassword(final String filePath, final String password) throws IOException {
    final File file = new File(filePath);
    final StringBuilder fileCopy = new StringBuilder();
    boolean lineFound = false;

    if (file.exists()) {
      final FileReader fileReader = new FileReader(file);

      try (BufferedReader br = new BufferedReader(fileReader)) {
        String fileLine;
        while ((fileLine = br.readLine()) != null) {

          if (fileLine.startsWith(PASSWORD_VAR)) {
            fileLine = buildPasswordLine(password);
            lineFound = true;
          }

          fileCopy.append(fileLine).append(System.lineSeparator());
        }
      }
    }

    if (!lineFound) {
      fileCopy
          .append(System.lineSeparator())
          .append(buildPasswordLine(password))
          .append(System.lineSeparator());
    }

    try (FileWriter fileWriter = new FileWriter(filePath)) {
      fileWriter.write(fileCopy.toString());
      log.debug("Saving file: {}", filePath);
      fileWriter.flush();
    }
  }

  private String buildPasswordLine(final String password) {
    return PASSWORD_VAR + "\"" + password + "\"";
  }
}
