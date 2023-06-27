package org.zjy.diveintoive.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zjy.diveintoive.DiveintoiveApplication;

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
//    public final String STORAGE_PATH = "/Users/zjy/Desktop/IVE/";
    public final String STORAGE_PATH = "/file/IVE/";



    public String getIVEServerResourcePath(){
        return "http://"+ networkUtil.getIP()+":"+port+"/ive/";
    }

}
