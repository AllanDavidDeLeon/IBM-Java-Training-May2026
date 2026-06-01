package org.eclipse.jakarta.infrastracture.repository;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jakarta.dto.ReportDto;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReportRepository {
	
    private List<ReportDto> reports = new ArrayList<>();
    private long nextId = 1;  
    
    public List<ReportDto> findAll() {
        return reports;
    }
    
    public void create(ReportDto report) {
        report.setId(nextId++);
        reports.add(report);
    }
    
    public void update(ReportDto updatedReport) {
        for (ReportDto r : reports) {
            if (r.getId().equals(updatedReport.getId())) {
                r.setTitle(updatedReport.getTitle());
                r.setDetail(updatedReport.getDetail());
                break;
            }
        }
    }
    
    public void delete(Long id) {
        reports.removeIf(r -> r.getId().equals(id));
    }
}