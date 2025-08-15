package org.example.requests;

/*
    RegisterUserRequest: register necessary info for the user including passwords
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @JsonProperty("username")
    private String username;
    @JsonProperty("passwordHash")
    private String passwordHash;
    private String email;
}
