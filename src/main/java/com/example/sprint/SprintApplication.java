package com.example.sprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
public class SprintApplication {

    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customizer(){
        return p -> {
            p.setOneIndexedParameters(true);  // 페이지가 0이 아닌 1부터 시작하게 설정
            p.setMaxPageSize(10);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SprintApplication.class, args);
    }

    @PostConstruct
    public void started() {
        // timezone UTC 셋팅
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

}
