package com.userservice.user.interfaces.rest.resources;

public record DeveloperResource(String id, String firstName, String lastName, String developerEmail, String description, String country, String phone, String specialties, String profileImgUrl) {
}
