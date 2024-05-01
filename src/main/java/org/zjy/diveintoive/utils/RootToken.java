package org.zjy.diveintoive.utils;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RootToken {

    @Getter
    private String root = null;

    private final String ROOT_TOKEN_PATH = "src/main/resources/.rootToken";

    private final FileReaderUtil fileReaderUtil;

    RootToken(FileReaderUtil fileReaderUtil) {
        this.fileReaderUtil = fileReaderUtil;
    }

    @PostConstruct
    public void post() {
        this.root = fileReaderUtil.readFileString(ROOT_TOKEN_PATH);
    }
}
