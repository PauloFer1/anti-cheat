package com.pfernand.anticheat.exception;

public class ServerConnectionException extends RuntimeException {

  private static final String TEMPLATE =  "Nao foi possivel conectar ao servidor do jogo";

  public ServerConnectionException() {
    super(TEMPLATE);
  }
}
