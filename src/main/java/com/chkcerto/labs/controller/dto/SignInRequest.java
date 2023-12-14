package com.chkcerto.labs.controller.dto;

import lombok.Data;

@Data
public class SignInRequest {

    private String email;

    private String password;
}
