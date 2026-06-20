package com.example.ems.service.impl;

import com.example.ems.dto.DashboardSummary;
import com.example.ems.entity.Attendance;
import com.example.ems.entity.LeaveRequest;
import com.example.ems.entity.LeaveStatus;
import com.example.ems.repository.AttendanceRepository;
import com.example.ems.repository.EmployeeRepository;
import com.example.ems.repository.LeaveRequestRepository;
import com.example.ems.service.DashboardService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;
    private final LeaveRequestRepository leaveRequestRepository;

    public DashboardServiceImpl(EmployeeRepository employeeRepository,
                                AttendanceRepository attendanceRepository,
                                LeaveRequestRepository leaveRequestRepository) {
        this.employeeRepository = employeeRepository;
        this.attendanceRepository = attendanceRepository;
        this.leaveRequestRepository = leaveRequestRepository;
    }

    @Override
    public DashboardSummary getDashboardSummary() {
        long totalEmployees = employeeRepository.count();
        LocalDate today = LocalDate.now();
        long presentToday = attendanceRepository.findByDate(today).stream()
                .filter(a -> a.getStatus() == com.example.ems.entity.AttendanceStatus.PRESENT)
                .count();
        long onLeave = leaveRequestRepository.findByStatus(LeaveStatus.APPROVED).stream()
                .filter(l -> !l.getStartDate().isAfter(today) && !l.getEndDate().isBefore(today))
                .count();
        List<Map<String, Object>> recentActivities = new ArrayList<>();
        attendanceRepository.findByDate(today).forEach(a -> {
            Map<String, Object> activity = new HashMap<>();
            activity.put("type", "Attendance");
            activity.put("employeeId", a.getEmployeeId());
            activity.put("status", a.getStatus());
            activity.put("date", a.getDate());
            recentActivities.add(activity);
        });
        leaveRequestRepository.findByStatus(LeaveStatus.PENDING).stream().limit(5).forEach(l -> {
            Map<String, Object> activity = new HashMap<>();
            activity.put("type", "Leave Request");
            activity.put("employeeId", l.getEmployeeId());
            activity.put("status", l.getStatus());
            activity.put("date", l.getStartDate());
            recentActivities.add(activity);
        });
        return new DashboardSummary(totalEmployees, presentToday, onLeave, recentActivities);
    }
}
