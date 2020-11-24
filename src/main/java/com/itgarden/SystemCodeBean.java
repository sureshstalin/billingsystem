package com.itgarden;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//@Configuration
//@Getter
//@Setter
//@Component
//@Configuration
//@ConfigurationProperties("system")
//@PropertySource(value = "classpath:systemcode.yml", factory = YamlPropertySourceFactory.class)

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
        return systemCodes.get(key);
    }
}
