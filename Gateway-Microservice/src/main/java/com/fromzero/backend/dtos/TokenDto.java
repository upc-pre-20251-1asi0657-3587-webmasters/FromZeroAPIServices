package com.fromzero.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenDto {
    private String id;
    private String userEmail;
    private List<String> roles;
    private String token;
}
