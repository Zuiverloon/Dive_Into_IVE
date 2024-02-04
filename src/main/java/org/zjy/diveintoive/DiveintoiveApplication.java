package org.zjy.diveintoive;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;



@SpringBootApplication
@RestController
@CrossOrigin
@EnableScheduling
public class DiveintoiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiveintoiveApplication.class, args);
    }

}
