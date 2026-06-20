package com.example.ems;

import com.example.ems.entity.Attendance;
import com.example.ems.entity.AttendanceStatus;
import com.example.ems.entity.Employee;
import com.example.ems.entity.LeaveRequest;
import com.example.ems.entity.LeaveStatus;
import com.example.ems.repository.AttendanceRepository;
import com.example.ems.repository.EmployeeRepository;
import com.example.ems.repository.LeaveRequestRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;
    private final LeaveRequestRepository leaveRequestRepository;

    public DataLoader(EmployeeRepository employeeRepository,
                      AttendanceRepository attendanceRepository,
                      LeaveRequestRepository leaveRequestRepository) {
        this.employeeRepository = employeeRepository;
        this.attendanceRepository = attendanceRepository;
        this.leaveRequestRepository = leaveRequestRepository;
    }

    @Override
    public void run(String... args) {
        if (employeeRepository.count() == 0) {
            Employee alice = employeeRepository.save(new Employee(null, "Alice Johnson", "alice.johnson@example.com", "555-0100", "HR", "HR Manager", new BigDecimal("75000"), LocalDate.of(2021, 3, 12)));
            Employee bob = employeeRepository.save(new Employee(null, "Bob Carter", "bob.carter@example.com", "555-0110", "Engineering", "Software Engineer", new BigDecimal("90000"), LocalDate.of(2022, 7, 5)));
            Employee cara = employeeRepository.save(new Employee(null, "Cara Nguyen", "cara.nguyen@example.com", "555-0120", "Finance", "Accountant", new BigDecimal("68000"), LocalDate.of(2023, 1, 9)));

            attendanceRepository.save(new Attendance(null, alice.getId(), LocalDate.now(), AttendanceStatus.PRESENT));
            attendanceRepository.save(new Attendance(null, bob.getId(), LocalDate.now(), AttendanceStatus.ABSENT));
            attendanceRepository.save(new Attendance(null, cara.getId(), LocalDate.now(), AttendanceStatus.PRESENT));

            leaveRequestRepository.save(new LeaveRequest(null, bob.getId(), "Sick Leave", LocalDate.now().plusDays(1), LocalDate.now().plusDays(3), "Flu symptoms", LeaveStatus.PENDING));
            leaveRequestRepository.save(new LeaveRequest(null, cara.getId(), "Annual Leave", LocalDate.now().plusDays(10), LocalDate.now().plusDays(14), "Family vacation", LeaveStatus.APPROVED));
        }
    }
}
