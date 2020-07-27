package com.pfernand.anticheat.exception;

import java.time.LocalDateTime;

public class CheatFoundException extends RuntimeException {

  private static final String TEMPLATE =  "\n*************************************************************\n"
                                        + "**                  ÉS UM CHEATEIRO                        **\n"
                                        + "**                 REMOVE O FICHEIRO:                      **\n"
                                        + "**                 %s                         **\n"
                                        + "** ------------------------------------------------------- **\n"
                                        + "**                 %s                   **\n"
                                        + "*************************************************************\n";

  public CheatFoundException(final String fileName) {
    super(String.format(TEMPLATE, fileName, LocalDateTime.now().toString()));
  }

  @Override
  public synchronized Throwable fillInStackTrace() {
    return this;
  }
}
