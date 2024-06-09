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

    @Value("${diveintoive.local.storage.path}")
    public String LOCAL_STORAGE_PATH;

    @Value("${diveintoive.remote.storage.path}")
    public String REMOTE_STORAGE_PATH;
    // ivebackup_1 is deprecated on 2023/12/10
    // ivebackup_2 is deprecated on 2024/06/09

    public final String HASH_PREFIX = "IVE_";



    public String getIVEServerResourcePath(){
        return "http://"+ networkUtil.getIP()+":"+port+"/ive/";
    }

}
