package com.userservice.user.interfaces.rest.resources;

public record UpdateEnterpriseResource(String enterpriseName, String enterpriseEmail, String enterpriseDescription, String enterpriseCountry, String enterpriseRuc, String enterprisePhone, String enterpriseWebsite, String enterpriseProfileImgUrl, String enterpriseSector) {
}
