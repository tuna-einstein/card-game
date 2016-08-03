package com.usp.kiss.server.service;

import com.usp.kiss.client.service.GetLoginEmailService;
import com.usp.kiss.server.common.CommonUtils;

public class GetLoginEmailServiceImpl implements GetLoginEmailService {

    public String getEmail() {
        String ownerEmail = CommonUtils.getOwnerEmail();
        if (ownerEmail == null) {
            return CommonUtils.getGoogleLoginUrl();
        }
        return ownerEmail;
    }
}
