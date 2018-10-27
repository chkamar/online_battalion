package cn.ch.battalion.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by karma on 2018/10/28.
 */
@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Value("${spring.data.mongodb.database}")
    private String database;


    @Bean
    public MongoTemplate mongoTemplate() {

        MongoClient mongoClient = new MongoClient(host,port);

        MongoTemplate template = new MongoTemplate(mongoClient, database);

        return template;

    }


}
