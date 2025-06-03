package com.fromzero.backend.comunication.interfaces.rest.resources;


import java.time.LocalDate;

public record SupportTicketResource(Long id, String title, String type, String description, Long sender, LocalDate creationDate){
}
