package org.zjy.diveintoive.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.zjy.diveintoive.service.CatcherService;
import org.zjy.diveintoive.service.CopyService;
import org.zjy.diveintoive.service.SyncService;
import org.zjy.diveintoive.utils.ConstantUtil;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IveImageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IveImageController.class);

    @Autowired
    CatcherService catcherService;
    @Autowired
    ConstantUtil constantUtil;

    @Autowired
    CopyService copyService;

    @Autowired
    SyncService syncService;


    @GetMapping("/inscatcherstoragepath")
    public String getInsCatcherStoragePath() {
        return catcherService.getPath();
    }

//    @PostMapping("/lizcopy")
//    public ResponseEntity<List<String>> lizCopy() {
//        return new ResponseEntity<>(copyService.localCopy(constantUtil.LIZ), HttpStatus.OK);
//    }
//
//    @PostMapping("/gaeulcopy")
//    public ResponseEntity<List<String>> gaeulCopy() {
//        return new ResponseEntity<>(copyService.localCopy(constantUtil.GAEUL), HttpStatus.OK);
//    }
//
//    @PostMapping("/lizgaeulcopy")
//    public ResponseEntity<List<String>> lizgaeulCopy() {
//        return new ResponseEntity<>(copyService.localCopy(constantUtil.LIZ_GAEUL), HttpStatus.OK);
//    }

    @GetMapping("/ive")
    public ResponseEntity<List<String>> iveImages() {
        return new ResponseEntity<>(catcherService.iveImages(), HttpStatus.OK);
    }

    @PostMapping("/copy")
    public ResponseEntity<List<String>> copy() {
        List<String> failedFileName = new ArrayList<>();
        failedFileName.addAll(copyService.localCopy(constantUtil.GAEUL));
        failedFileName.addAll(copyService.localCopy(constantUtil.LIZ));
        failedFileName.addAll(copyService.localCopy(constantUtil.LIZ_GAEUL));
        failedFileName.addAll(copyService.localCopy(constantUtil.YOON));
        return new ResponseEntity<>(failedFileName, HttpStatus.OK);
    }

    @PostMapping("/sync")
    public ResponseEntity<List<String>> sync() {
        return new ResponseEntity<>(syncService.sync(), HttpStatus.OK);
    }

    @PostMapping("/checkAllBackup")
    public ResponseEntity<Boolean> check() {
        return new ResponseEntity<>(syncService.check(), HttpStatus.OK);
    }
}
