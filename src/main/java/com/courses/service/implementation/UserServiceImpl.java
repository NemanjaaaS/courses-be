package com.courses.service.implementation;

import com.courses.common.service.JwtService;
import com.courses.exception.NotFoundException;
import com.courses.models.User;
import com.courses.repositories.UserRepository;
import com.courses.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public int getUserIdByEmail(String email) {
        return getUserByEmail(email).getId();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }

    @Override
    public User getUserByToken(String token) {
        return getUserByEmail(jwtService.getEmailFromUnsplitToken(token));
    }

}
