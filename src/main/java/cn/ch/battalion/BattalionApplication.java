package cn.ch.battalion;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableCaching
@MapperScan("cn.ch.battalion.core.dao")
public class BattalionApplication {

	public static void main(String[] args) {
		SpringApplication.run(BattalionApplication.class, args);
	}
}

