package com.NINNong.nonggu.service;

import com.NINNong.nonggu.dto.UserSignupRequest;
import com.NINNong.nonggu.entity.UserEntity;
import com.NINNong.nonggu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(UserSignupRequest request) {

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        request.replaceToEncodedPassword(encodedPassword);

        UserEntity userEntity = UserSignupRequest.toEntity(request);

        userRepository.save(userEntity);
    }
}
