package com.app.library.service;

import com.app.library.model.dto.CommonDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class BusinessObjectVersionValidatorService {

    public String validate(CommonDto commonDto) {
        int businessVersion = commonDto.getBusinessObjectVersion();
        if (businessVersion < 0) {
            return "Business Object Version cannot negative";
        }
        return StringUtils.EMPTY;
    }
}
