package com.ddct.acmecars.config;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;

import lombok.extern.slf4j.Slf4j;

@EnableAsync
@Configuration
@Slf4j
public class AsyncConfig extends AsyncConfigurerSupport implements InitializingBean {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    @Override
    public Executor getAsyncExecutor() {
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        executor.setConcurrencyLimit(100);
        executor.setThreadNamePrefix("MyExecutor-");
        return executor;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Executor Beans: {}", applicationContext.getBeansOfType(Executor.class));
        log.info("TaskExecutor Beans: {}", applicationContext.getBeansOfType(TaskExecutor.class));
    }
}
