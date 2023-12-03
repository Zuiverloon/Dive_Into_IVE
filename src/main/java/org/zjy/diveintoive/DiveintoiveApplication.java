package org.zjy.diveintoive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zjy.diveintoive.service.CatcherService;
import org.zjy.diveintoive.service.CopyService;
import org.zjy.diveintoive.service.HashService;
import org.zjy.diveintoive.utils.ConstantUtil;

import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin
public class DiveintoiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiveintoiveApplication.class, args);
    }

}
