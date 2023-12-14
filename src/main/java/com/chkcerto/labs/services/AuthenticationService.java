package com.chkcerto.labs.services;

import com.chkcerto.labs.controller.dto.JwtAuthenticationResponse;
import com.chkcerto.labs.controller.dto.RefreshTokenRequest;
import com.chkcerto.labs.controller.dto.SignInRequest;
import com.chkcerto.labs.controller.dto.SignUpRequest;
import com.chkcerto.labs.entities.User;

public interface AuthenticationService  {

    User signUp(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
