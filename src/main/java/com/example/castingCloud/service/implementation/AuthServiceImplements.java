package com.example.castingCloud.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.castingCloud.common.ResponseMessage;
import com.example.castingCloud.dto.request.auth.ActorSignUpDto;
import com.example.castingCloud.dto.response.ActorSignUpResponseDto;
import com.example.castingCloud.dto.response.ResponseDto;
import com.example.castingCloud.entity.ActorEntity;
import com.example.castingCloud.provider.TokenProvider;
import com.example.castingCloud.repository.ActorRepository;
import com.example.castingCloud.service.AuthService;

@Service
public class AuthServiceImplements implements AuthService {
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private ActorRepository actorRepository;

    private static final String DEFAULT_NICKNAME = "user";
    
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseDto<ActorSignUpResponseDto> signUp(ActorSignUpDto dto) {
        ActorSignUpResponseDto data = null;

        String actorEmail = dto.getActorEmail();
        String actorPassword = dto.getActorPassword();
        String actorNickName = dto.getActorNickName();

        try {
            boolean hasActorEmail = actorRepository.existsByActorEmail(actorEmail);
            if(hasActorEmail) return ResponseDto.setFailed(ResponseMessage.EXIST_EMAIL);

            String encodePassword = passwordEncoder.encode(actorPassword);
            dto.setActorPassword(encodePassword);

            if (actorNickName == null || actorNickName.isEmpty()) {
                actorNickName = DEFAULT_NICKNAME;
                }
            
            ActorEntity actorEntity = new ActorEntity(dto);
            actorEntity.setActorNickName(generateUniqueNickname(actorNickName));
            actorRepository.save(actorEntity);

            

        }catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    private String generateUniqueNickname(String baseNickname) {
        String newActorNickName = baseNickname;
        int suffix = 1;

        while (actorRepository.existsByActorNickName(newActorNickName)) {
            newActorNickName = baseNickname + suffix;
            suffix++;
        }

        return newActorNickName;
    }
}
