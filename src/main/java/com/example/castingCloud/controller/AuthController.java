package com.example.castingCloud.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.castingCloud.common.ApiPattern;
import com.example.castingCloud.dto.request.auth.ActorSignUpDto;
import com.example.castingCloud.dto.request.auth.DirectorSignUpDto;
import com.example.castingCloud.dto.response.ResponseDto;
import com.example.castingCloud.dto.response.auth.ActorSignUpResponseDto;
import com.example.castingCloud.dto.response.auth.DirectorSignUpResponseDto;
import com.example.castingCloud.service.AuthService;

@RestController
@RequestMapping(ApiPattern.AUTH)
public class AuthController {
    private AuthService authService;

    private final String ACTOR_SIGN_UP = "/actor-sign-up";
    private final String DIRECTOR_SIGN_UP = "/director-sign-up";
    
    @PostMapping(ACTOR_SIGN_UP)
    public ResponseDto<ActorSignUpResponseDto> signUp(@Valid @RequestBody ActorSignUpDto requestBody) {
        ResponseDto<ActorSignUpResponseDto> response = authService.actorSignUp(requestBody);
        return response;
    }

    @PostMapping(DIRECTOR_SIGN_UP)
    public ResponseDto<DirectorSignUpResponseDto> signUp(@Valid @RequestBody DirectorSignUpDto requestBody) {
        ResponseDto<DirectorSignUpResponseDto> response = authService.directorSignUp(requestBody);
        return response;
    }
}
