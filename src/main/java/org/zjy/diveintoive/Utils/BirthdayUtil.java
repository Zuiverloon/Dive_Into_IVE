package org.zjy.diveintoive.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BirthdayUtil {
    @Autowired
    ConstantUtil constantUtil;
    public String getBirthday(String member){
        if (Objects.equals(member, constantUtil.LIZ)){
            return "2004-11-21";
        }
        else if (Objects.equals(member, constantUtil.GAEUL)){
            return "2002-09-24";
        }
        return "unknown";
    }
}
