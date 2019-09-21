package com.serh.trackmoney;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableJpaRepositories
public class TrackMoneyApplication {

    public static void main(final String[] args) {
        run(TrackMoneyApplication.class, args);

    }
}
