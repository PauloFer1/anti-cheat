package com.pfernand.anticheat.services;

import com.pfernand.anticheat.exception.CheatFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileFinder {

  @Value("${app.forbidden-files}")
  private final List<String> forbiddenFiles;

  public void findCheatFiles() throws IOException {
    log.debug("Searching for file: {}", forbiddenFiles);
    try (Stream<Path> stream = Files.walk(Paths.get(""), 1)) {
      stream
          .filter(file -> !file.toFile().isDirectory())
          .map(Path::getFileName)
          .map(Path::toString)
          .map(String::toLowerCase)
          .filter(forbiddenFiles::contains)
          .findFirst()
          .ifPresent(file -> {
            throw new CheatFoundException(file);
          });
    }


  }

}
