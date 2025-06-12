package com.userservice.user.interfaces.rest.resources;

public record UpdateDeveloperResource(String firstName, String lastName, String developerEmail, String description, String country, String phone, String specialties, String profileImgUrl) {
}
