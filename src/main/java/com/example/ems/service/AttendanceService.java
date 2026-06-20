package com.example.ems.service;

import com.example.ems.entity.Attendance;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceService {
    Attendance markAttendance(Attendance attendance);
    List<Attendance> getAttendanceByEmployee(Long employeeId);
    List<Attendance> getAttendanceByDate(LocalDate date);
    Optional<Attendance> getAttendanceById(Long id);
    long countByDate(LocalDate date);
}
