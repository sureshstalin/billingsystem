package com.itgarden;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/*
 * Created by Suresh Stalin on 24 / Nov / 2020.
 */

@Component
public class SystemCodeBean {

    public Map<String, String> systemCodes = new HashMap<>();

    @Autowired
    private SystemCodeConfiguration systemCodeConfiguration;

    public String getSystemCodes(String key) {
        systemCodes.put(systemCodeConfiguration.getEmpCodeType(), systemCodeConfiguration.getEmpCodePrefix());
        systemCodes.put(systemCodeConfiguration.getCusCodeType(), systemCodeConfiguration.getCusCodePrefix());
        systemCodes.put(systemCodeConfiguration.getVenCodeType(), systemCodeConfiguration.getVenCodePrefix());
        systemCodes.put(systemCodeConfiguration.getCatCodeType(), systemCodeConfiguration.getCatCodePrefix());
        systemCodes.put(systemCodeConfiguration.getOfrCodeType(), systemCodeConfiguration.getOfrCodePrefix());
        systemCodes.put(systemCodeConfiguration.getPrdCodeType(), systemCodeConfiguration.getPrdCodePrefix());
        systemCodes.put(systemCodeConfiguration.getBcdCodeType(), systemCodeConfiguration.getBcdCodePrefix());
        systemCodes.put(systemCodeConfiguration.getOrgCodeType(), systemCodeConfiguration.getOrgCodePrefix());
        systemCodes.put(systemCodeConfiguration.getPurCodeType(),systemCodeConfiguration.getPurCodePrefix());
        systemCodes.put(systemCodeConfiguration.getBilCodeType(),systemCodeConfiguration.getBilCodePrefix());
        return systemCodes.get(key);
    }
}
