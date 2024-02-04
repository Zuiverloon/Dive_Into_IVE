package org.zjy.diveintoive.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantUtil {
    // private final Logger logger = LogManager.getLogger(DiveintoiveApplication.class);

    @Autowired
    private NetworkUtil networkUtil;

    @Value("${fileserver.port}")
    private int port;

    public final String LIZ = "Liz";
    public final String GAEUL = "Gaeul";
    public final String LIZ_GAEUL = "Liz_Gaeul";
    public final String YOON = "Yoon";
    public final String LOCAL_STORAGE_PATH = "/Users/zjy/Desktop/IVE/";
    public final String REMOTE_STORAGE_PATH = "/Users/zjy/Desktop/ivebackup_2/content/";
    // ivebackup_1 is deprecated on 2023/12/10


//    public final String STORAGE_PATH = "/file/IVE/";
    public final String HASH_PREFIX = "IVE_";



    public String getIVEServerResourcePath(){
        return "http://"+ networkUtil.getIP()+":"+port+"/ive/";
    }

}
