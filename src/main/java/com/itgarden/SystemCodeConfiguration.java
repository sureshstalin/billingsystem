package com.itgarden;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/*
 * Created by Suresh Stalin on 24 / Nov / 2020.
 */

@Getter
@Setter
@Component
@Configuration
@ConfigurationProperties("system")
@PropertySource(value = "classpath:systemcode.yml", factory = YamlPropertySourceFactory.class)
public class SystemCodeConfiguration {

    public String empCodePrefix;
    public String empCodeType;

    public String cusCodePrefix;
    public String cusCodeType;

    public String venCodePrefix;
    public String venCodeType;

    public String catCodePrefix;
    public String catCodeType;

    public String ofrCodePrefix;
    public String ofrCodeType;

    public String prdCodePrefix;
    public String prdCodeType;

    public String bcdCodePrefix;
    public String bcdCodeType;

    public String  orgCodePrefix;
    public String orgCodeType;

    public String purCodePrefix;
    public String purCodeType;

    public String bilCodePrefix;
    public String bilCodeType;
}
