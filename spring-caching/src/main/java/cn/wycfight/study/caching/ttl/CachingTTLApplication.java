package cn.wycfight.study.caching.ttl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 开启定时任务
 */
@SpringBootApplication
@EnableScheduling
public class CachingTTLApplication {
  public static void main(String[] args) {
    SpringApplication.run(CachingTTLApplication.class, args);
  }
}