package com.internship.portal.user.service;

import com.internship.portal.user.dto.EmotionDTO;
import com.internship.portal.microservices.commons.exception.BaseBusinessException;
import com.internship.portal.user.model.Emotion;
import com.internship.portal.user.repository.EmotionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private EmotionRepository emotionRepository;

    public List<Emotion> getReports(){
        try{
            List<Emotion> reports = emotionRepository.findAll();
            log.info("Relatorios encontrados: [{}]", reports);
            return reports;
        } catch (Exception e) {
            throw new BaseBusinessException("ERROR_REPORT_0001", "Falha ao consultar relatórios", HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    public Emotion getReport(Long reportId){
        try{
            Emotion report = emotionRepository.findById(reportId).orElse(null);
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
            emotionRepository.deleteById(reportId);
            log.info("Relatorio deletado: [{}]", reportId);
        } catch (BaseBusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseBusinessException("ERROR_REPORT_0004", "Falha ao apagar relatório", HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    public Emotion postReport(EmotionDTO reportDTO){
        try{
            Emotion report = new Emotion(reportDTO.getStudentName(), reportDTO.getGrade());
            Emotion savedReport = emotionRepository.save(report);
            log.info("Relatorio salvo: [{}]", savedReport);
            return savedReport;
        } catch (Exception e) {
            throw new BaseBusinessException("ERROR_REPORT_0005", "Falha ao criar relatório", HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    public Emotion putReport(EmotionDTO reportDTO, Long reportId){
        try{
            Emotion report = this.getReport(reportId);
            report.setStudentName(reportDTO.getStudentName());
            report.setGrade(reportDTO.getGrade());
            Emotion savedReport = emotionRepository.save(report);
            log.info("Relatorio atualizado: [{}]", savedReport);
            return savedReport;
        } catch (BaseBusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseBusinessException("ERROR_REPORT_0006", "Falha ao apagar relatório", HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }
}
