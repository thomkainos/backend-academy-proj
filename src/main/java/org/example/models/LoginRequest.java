package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @NoArgsConstructor @AllArgsConstructor
public class LoginRequest {
    @NotEmpty
    String username;

    @NotEmpty
    String password;
}
