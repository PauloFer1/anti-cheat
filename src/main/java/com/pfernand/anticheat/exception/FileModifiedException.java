package com.pfernand.anticheat.exception;

import java.time.LocalDateTime;

public class FileModifiedException extends RuntimeException {

  private static final String TEMPLATE =  "\n*************************************************************\n"
      + "**                  ÉS UM CHEATEIRO                        **\n"
      + "**         Ficheiro executável foi modificado:             **\n"
      + "**                     %s                         **\n"
      + "** ------------------------------------------------------- **\n"
      + "**                 %s                   **\n"
      + "*************************************************************\n";

  public FileModifiedException(final String fileName) {
    super(String.format(TEMPLATE, fileName, LocalDateTime.now().toString()));
  }

  @Override
  public synchronized Throwable fillInStackTrace() {
    return this;
  }

}
