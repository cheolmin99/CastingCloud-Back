package com.example.castingCloud.service;

import com.example.castingCloud.dto.request.auth.ActorSignUpDto;
import com.example.castingCloud.dto.response.ActorSignUpResponseDto;
import com.example.castingCloud.dto.response.ResponseDto;

public interface AuthService {
    public ResponseDto<ActorSignUpResponseDto> signUp(ActorSignUpDto dto);    
}
