package org.eclipse.jakarta.dto;

public class ReportDto {
    private Long id;      
    private String title;
    private String detail;
    
    public ReportDto() {}
    
    public ReportDto(Long id, String title, String detail) {
        this.id = id;
        this.title = title;
        this.detail = detail;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDetail() {
        return detail;
    }
    
    public void setDetail(String detail) {
        this.detail = detail;
    }
}