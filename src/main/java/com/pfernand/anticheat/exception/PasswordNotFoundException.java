package com.pfernand.anticheat.exception;

public class PasswordNotFoundException extends RuntimeException {

  private static final String TEMPLATE =  "Password nao encontrada no ficheiro de config: %s";

  public PasswordNotFoundException(String configPath) {
    super(String.format(TEMPLATE, configPath));
  }
}
