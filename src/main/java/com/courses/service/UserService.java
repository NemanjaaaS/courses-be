package com.courses.service;

import com.courses.dto.UserTableDTO;
import com.courses.models.User;

import java.util.List;

public interface UserService {

    int getUserIdByEmail(String email);
    User getUserByEmail(String email);
    User getUserByToken(String token);
    List<UserTableDTO> getAllUsersForTable();

}
