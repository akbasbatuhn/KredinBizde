package com.patika.userservice.config;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class MongoConfig {

    @Bean
    public Converter<String, ObjectId> stringToObjectIdConverter() {
        return new Converter<String, ObjectId>() {
            @Override
            public ObjectId convert(String source) {
                return new ObjectId(source);
            }
        };
    }
}
