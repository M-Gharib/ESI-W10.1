package com.esi.authservice.users.dto;

import jakarta.persistence.Id;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
public class UserDto {
    @Id
    private String name;
    private String password;
}
