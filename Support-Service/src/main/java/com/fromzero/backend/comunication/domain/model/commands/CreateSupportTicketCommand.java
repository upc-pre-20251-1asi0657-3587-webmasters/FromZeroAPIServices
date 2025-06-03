package com.fromzero.backend.comunication.domain.model.commands;


public record CreateSupportTicketCommand(String title, String type, String description, Long sender){
}
