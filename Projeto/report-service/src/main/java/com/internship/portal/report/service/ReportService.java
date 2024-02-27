package com.internship.portal.report.service;

import com.internship.portal.report.config.ReportProperties;
import com.internship.portal.report.dto.ReportDTO;
import com.internship.portal.microservices.commons.exception.BaseBusinessException;
import com.internship.portal.report.model.Report;
import com.internship.portal.report.repository.ReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportProperties reportProperties;

    public List<Report> getReports(){
        try{
            List<Report> reports = reportRepository.findAll();
            log.info("Relatorios encontrados: [{}]", reports);
            return reports;
        } catch (Exception e) {
            throw new BaseBusinessException("ERROR_REPORT_0001", "Falha ao consultar relatórios", HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    public Report getReport(Long reportId){
        try{
            Report report = reportRepository.findById(reportId).orElse(null);
            if (report == null) {
                throw new BaseBusinessException("ERROR_REPORT_0003", "Não ha relatorio com o id fornecido", HttpStatus.FORBIDDEN);
            }
            log.info("Relatorio encontrado: [{}]", report);
            return report;
        } catch (BaseBusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseBusinessException("ERROR_REPORT_0002", "Falha ao consultar relatório", HttpStatus.INTERNAL_SERVER_ERROR, e);
        }

    }

    public void deleteReport(Long reportId){
        try{
            this.getReport(reportId);
            reportRepository.deleteById(reportId);
            log.info("Relatorio deletado: [{}]", reportId);
        } catch (BaseBusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseBusinessException("ERROR_REPORT_0004", "Falha ao apagar relatório", HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    public Report postReport(ReportDTO reportDTO){
        try{
            Report report = new Report(reportDTO.getStudentName(), reportDTO.getGrade());
            Report savedReport = reportRepository.save(report);
            log.info("Relatorio salvo: [{}]", savedReport);
            return savedReport;
        } catch (Exception e) {
            throw new BaseBusinessException("ERROR_REPORT_0005", "Falha ao criar relatório", HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    public Report putReport(ReportDTO reportDTO, Long reportId){
        try{
            Report report = this.getReport(reportId);
            report.setStudentName(reportDTO.getStudentName());
            report.setGrade(reportDTO.getGrade());
            Report savedReport = reportRepository.save(report);
            log.info("Relatorio atualizado: [{}]", savedReport);
            return savedReport;
        } catch (BaseBusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseBusinessException("ERROR_REPORT_0006", "Falha ao apagar relatório", HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    public String getProperty(){
        return reportProperties.getMessage();
    }
}
