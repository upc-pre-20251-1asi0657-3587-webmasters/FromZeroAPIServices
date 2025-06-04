package com.userservice.user.interfaces.rest.resources;

public record EnterpriseResource(String enterpriseName, String enterpriseEmail, String enterpriseDescription, String enterpriseCountry, String enterpriseRuc, String enterprisePhone, String enterpriseWebsite, String enterpriseProfileImgUrl, String enterpriseSector) {
}
