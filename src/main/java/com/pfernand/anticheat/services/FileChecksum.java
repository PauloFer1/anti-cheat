package com.pfernand.anticheat.services;

import com.pfernand.anticheat.exception.FileModifiedException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileChecksum {

  private final String originalFileChecksum;

  public void verifyExecFile(final String filePath) throws NoSuchAlgorithmException, IOException {
    long checksum = 0L;
    try (InputStream fileInputStream = Files.newInputStream(Paths.get(filePath))) {
      CheckedInputStream checkedInputStream = new CheckedInputStream(fileInputStream, new CRC32());
      byte[] buffer = new byte[1024];
      while (checkedInputStream.read(buffer, 0, buffer.length) >= 0) {

      }
      checksum = checkedInputStream.getChecksum().getValue();
    }
    log.debug(String.valueOf(checksum));
    if (!originalFileChecksum.equals(String.valueOf(checksum))) {
      throw new FileModifiedException(filePath);
    }
  }
}
