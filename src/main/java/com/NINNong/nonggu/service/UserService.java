package com.NINNong.nonggu.service;


import com.NINNong.nonggu.dto.UserSignupRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

   void signUp(UserSignupRequest request);


}
