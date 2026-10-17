package de.bg.lagersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BgLagerSystemApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BgLagerSystemApplication.class, args);
    }
}
