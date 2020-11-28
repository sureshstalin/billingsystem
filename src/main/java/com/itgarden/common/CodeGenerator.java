package com.itgarden.common;

import com.itgarden.SystemCodeBean;
import com.itgarden.common.staticdata.CodeType;
import com.itgarden.entity.AppEntityCode;
import com.itgarden.repository.AppEntityCodeRepository;
import com.itgarden.repository.SystemCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/*
 * Created by Suresh Stalin on 26 / Oct / 2020.
 */

@Component
public class CodeGenerator {

    @Autowired
    private AppEntityCodeRepository appEntityCodeRepository;

    @Autowired
    private SystemCodeRepository systemCodeRepository;

    @Autowired
    private SystemCodeBean systemCodeBean;

    public String newCode(CodeType codeType) {
        String code = "";
        AppEntityCode appEntityCode = null;
        do {
            String newCode = getCode(codeType.name());
            appEntityCode = appEntityCodeRepository.findByCode(newCode);
            if (appEntityCode == null) {
                code = newCode;
            }
        } while (appEntityCode != null);
        appEntityCode = new AppEntityCode();
        appEntityCode.setCode(code);
        appEntityCode.setCodeType(codeType);
        appEntityCodeRepository.save(appEntityCode);
        return appEntityCode.getCode();
    }

    private String getCode(String codeType) {
        Random r = new Random(System.currentTimeMillis());
        int id = ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
        String codePrefix = systemCodeBean.getSystemCodes(codeType);
        return codePrefix + id;
    }
}
