package org.zjy.diveintoive.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.zjy.diveintoive.DiveintoiveApplication;

@Component
public class ConstantUtil {
    private final Logger logger = LogManager.getLogger(DiveintoiveApplication.class);

    @Autowired
    private NetworkUtil networkUtil;

    @Value("${server.port}")
    private int port;

//    ConstantUtil(@Value("${server.port}") int port){
//        this.IVE_SERVER_RESOURCE_PATH = "http://"+ networkUtil.ip+":"+port+"/ive/";
//        logger.info("IVE_SERVER_RESOURCE_PATH="+IVE_SERVER_RESOURCE_PATH);
//    }
    public String IVE_SERVER_RESOURCE_PATH;
    public final String LIZ = "Liz";
    public final String GAEUL = "Gaeul";
    public final String LIZ_GAEUL = "Liz_Gaeul";
    public final String STORAGE_PATH = "/Users/zjy/Desktop/IVE/";



    public String getIVEServerResourcePath(){
        return "http://"+ networkUtil.getIP()+":"+port+"/ive/";
    }

}
