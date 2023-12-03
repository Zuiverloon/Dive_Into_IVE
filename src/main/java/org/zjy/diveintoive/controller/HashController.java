package org.zjy.diveintoive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.zjy.diveintoive.service.HashService;

import java.net.InetAddress;
import java.util.List;

@Controller
public class HashController {

    @Autowired
    HashService hashService;

    @PostMapping("/hash")
    public ResponseEntity<Integer> genHash() {
        return new ResponseEntity<>(hashService.genHash(), HttpStatus.OK);
    }

    @GetMapping("/hash")
    public ResponseEntity<List<String>> getHash() {
        return new ResponseEntity<>(hashService.getHash(), HttpStatus.OK);
    }

    @DeleteMapping("/hash")
    public ResponseEntity<Integer> deleteHash() {
        return new ResponseEntity<>(hashService.deleteHash(), HttpStatus.OK);
    }

}
