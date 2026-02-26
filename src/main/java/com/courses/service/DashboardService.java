package com.courses.service;

import com.courses.dto.DashboardDTO;
import com.courses.dto.UserDashboardDTO;

public interface DashboardService {

    DashboardDTO getAdminDashboard();

    UserDashboardDTO getUserDashboard(String token);
}
