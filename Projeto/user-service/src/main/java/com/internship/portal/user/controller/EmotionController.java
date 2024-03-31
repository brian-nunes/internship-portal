package com.internship.portal.user.controller;

import com.internship.portal.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.internship.portal.user.service.UserService;
import com.internship.portal.user.model.Report;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/message")
    public ResponseEntity<String> getMessage() {
        String message = userService.getProperty();
        return ResponseEntity.ok(message);
    }

    @GetMapping("/report")
    public ResponseEntity<List<Report>> getReports() {
        List<Report> reports = userService.getReports();
        return ResponseEntity.ok(reports);
    }

    @PostMapping("/report")
    public ResponseEntity<Report> postReport(@RequestBody UserDTO userDTO) {
        Report report = userService.postReport(userDTO);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/report/{reportId}")
    public ResponseEntity<Report> getReport(@PathVariable Long reportId) {
        Report report = userService.getReport(reportId);
        return ResponseEntity.ok(report);
    }

    @PutMapping("/report/{reportId}")
    public ResponseEntity<Report>  putReport(@RequestBody UserDTO userDTO, @PathVariable Long reportId) {
        Report report = userService.putReport(userDTO, reportId);
        return ResponseEntity.ok(report);
    }

    @DeleteMapping("/report/{reportId}")
    public ResponseEntity<Boolean> deleteReport(@PathVariable Long reportId) {
        userService.deleteReport(reportId);
        return ResponseEntity.ok(true);
    }
}