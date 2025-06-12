package com.userservice.user.interfaces.rest.resources;

public record UpdateEnterpriseResource(String enterpriseName, String enterpriseEmail, String description, String country, String ruc, String phone, String website, String profileImgUrl, String sector) {
}
