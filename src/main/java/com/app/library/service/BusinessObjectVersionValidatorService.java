package com.app.library.service;

import com.app.library.model.dto.BusinessObjectVersionDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class BusinessObjectVersionValidatorService {

    public String validate(BusinessObjectVersionDto domain) {
        int businessVersion = domain.businessObjectVersion();
        if (businessVersion < 0) {
            return "Business Object Version cannot negative";
        }
        return StringUtils.EMPTY;
    }
}
