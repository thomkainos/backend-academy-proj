package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter @NoArgsConstructor @AllArgsConstructor
public class LoginRequest {
    @NotNull
    String username;

    @NotNull
    String password;
}
