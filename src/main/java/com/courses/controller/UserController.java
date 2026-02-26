package com.courses.controller;

import com.courses.dto.UserDashboardDTO;
import com.courses.models.User;
import com.courses.service.DashboardService;
import com.courses.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${courses.api.url}/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final DashboardService dashboardService;
    @GetMapping("/me")
    public ResponseEntity<User> seyHello(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.getUserByToken(token));
    }
    @GetMapping("/dashboard")
    public UserDashboardDTO getUserDashboard(@RequestHeader("Authorization") String token) {
        return dashboardService.getUserDashboard(token);
    }


}
