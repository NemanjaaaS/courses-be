package com.courses.service;

import com.courses.models.User;

public interface UserService {

    int getUserIdByEmail(String email);
    User getUserByEmail(String email);
    User getUserByToken(String token);

}
