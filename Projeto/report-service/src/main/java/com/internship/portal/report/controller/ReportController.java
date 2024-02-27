package com.internship.portal.report.controller;

import com.internship.portal.report.dto.ReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.internship.portal.report.service.ReportService;
import com.internship.portal.report.model.Report;

import java.util.List;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/message")
    public ResponseEntity<String> getMessage() {
        String message = reportService.getProperty();
        return ResponseEntity.ok(message);
    }

    @GetMapping("/report")
    public ResponseEntity<List<Report>> getReports() {
        List<Report> reports = reportService.getReports();
        return ResponseEntity.ok(reports);
    }

    @PostMapping("/report")
    public ResponseEntity<Report> postReport(@RequestBody ReportDTO reportDTO) {
        Report report = reportService.postReport(reportDTO);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/report/{reportId}")
    public ResponseEntity<Report> getReport(@PathVariable Long reportId) {
        Report report = reportService.getReport(reportId);
        return ResponseEntity.ok(report);
    }

    @PutMapping("/report/{reportId}")
    public ResponseEntity<Report>  putReport(@RequestBody ReportDTO reportDTO, @PathVariable Long reportId) {
        Report report = reportService.putReport(reportDTO, reportId);
        return ResponseEntity.ok(report);
    }

    @DeleteMapping("/report/{reportId}")
    public ResponseEntity<Boolean> deleteReport(@PathVariable Long reportId) {
        reportService.deleteReport(reportId);
        return ResponseEntity.ok(true);
    }
}