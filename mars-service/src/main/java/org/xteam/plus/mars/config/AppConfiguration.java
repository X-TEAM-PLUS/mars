package org.xteam.plus.mars.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.xteam.plus.mars.common.DateTimeConverter;

import java.util.Date;

@Configuration
public class AppConfiguration {
    @Bean
    public Converter<String, Date> addNewConvert() {
        return new DateTimeConverter();
    }
}
