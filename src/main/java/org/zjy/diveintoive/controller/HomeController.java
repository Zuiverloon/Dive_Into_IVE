package org.zjy.diveintoive.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public ResponseEntity<String> home() {
        LOGGER.info("Info Message");
        LOGGER.debug("Debug Message!");
        LOGGER.warn("Warn Message!");
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }
}
