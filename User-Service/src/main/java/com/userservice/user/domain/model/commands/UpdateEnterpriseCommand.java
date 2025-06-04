package com.userservice.user.domain.model.commands;

import com.userservice.user.domain.model.valueobjects.enterprise.*;

public record UpdateEnterpriseCommand(EnterpriseId enterpriseId, EnterpriseName enterpriseName, EnterpriseEmail enterpriseEmail, EnterpriseDescription enterpriseDescription, EnterpriseCountry enterpriseCountry, EnterpriseRuc enterpriseRuc, EnterprisePhone enterprisePhone, EnterpriseWebsite enterpriseWebsite, EnterpriseProfileImgUrl enterpriseProfileImgUrl, EnterpriseSector enterpriseSector) {
}
