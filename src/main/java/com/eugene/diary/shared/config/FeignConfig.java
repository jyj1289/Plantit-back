package com.eugene.diary.shared.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "com.eugene.diary.infrastructure")
@Configuration
public class FeignConfig {
}
