package com.minhsonle.aiagent;

import org.springframework.boot.SpringApplication;

public class TestAiAgentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(AiAgentServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
