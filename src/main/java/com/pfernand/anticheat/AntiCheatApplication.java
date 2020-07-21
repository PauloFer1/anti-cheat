package com.pfernand.anticheat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ComponentScan
public class AntiCheatApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AntiCheatApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("EXECUTING : command line runner");

		for (int i = 0; i < args.length; ++i) {
			log.info("args[{}]: {}", i, args[i]);
		}
	}
}
