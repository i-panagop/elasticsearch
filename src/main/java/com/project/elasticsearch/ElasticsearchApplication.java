package com.project.elasticsearch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
public class ElasticsearchApplication {

	@Value("${index.task.executor.threads.number}")
	private Integer indexTaskExecutorThreadsNumber;

	@Value("${index.task.executor.max.pool.size}")
	private Integer indexTaskExecutorMaxPoolSize;

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchApplication.class, args);
	}

	@Bean(name = "indexExecutor")
	public TaskExecutor indexExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(indexTaskExecutorThreadsNumber);
		executor.setMaxPoolSize(indexTaskExecutorMaxPoolSize);
		executor.setQueueCapacity(2000);
		executor.initialize();
		return executor;
	}

}
