package com.example.ems.service;

import com.example.ems.entity.LeaveRequest;

import java.util.List;
import java.util.Optional;

public interface LeaveRequestService {
    LeaveRequest applyLeave(LeaveRequest leaveRequest);
    List<LeaveRequest> getLeaveRequests();
    List<LeaveRequest> getLeaveRequestsByEmployee(Long employeeId);
    Optional<LeaveRequest> getLeaveRequestById(Long id);
    LeaveRequest updateLeaveStatus(Long id, String status);
}
