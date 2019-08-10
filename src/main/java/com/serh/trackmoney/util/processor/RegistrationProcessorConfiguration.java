package com.serh.trackmoney.util.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RegistrationProcessorConfiguration {

    @Autowired
    @Qualifier("email")
    private RegistrationProcessor emailProcessor;
    @Autowired
    @Qualifier("password")
    private RegistrationProcessor passwordProcessor;

    @Bean
    @Primary
    public RegistrationProcessor registrationProcessor() {
        RegistrationProcessor processor = emailProcessor;
        processor.setNextProcessor(passwordProcessor);
        return processor;
    }
}
