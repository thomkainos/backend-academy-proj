package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class LoginRequest {
    String username;
    String password;

    @JsonCreator
    public LoginRequest(
            final @JsonProperty("username") String username,
            final @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }
}
