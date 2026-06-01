package org.eclipse.jakarta.backingbean;
import org.eclipse.jakarta.dto.ReportDto;
import org.eclipse.jakarta.infrastracture.repository.ReportRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
@Named
@RequestScoped
public class ReportUpdateBean {
	
    private Long id;
    private String title;
    private String detail;
    
    @Inject
    private ReportRepository reportRepository;
    
    @PostConstruct
    public void init() {
        String idParam = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");
        if (idParam != null) {
            id = Long.valueOf(idParam);
            for (ReportDto r : reportRepository.findAll()) {
                if (r.getId().equals(id)) {
                    title = r.getTitle();
                    detail = r.getDetail();
                    break;
                }
            }
        }
    }
    public String update() {
        for (ReportDto r : reportRepository.findAll()) {
            if (r.getId().equals(id)) {
                r.setTitle(title);
                r.setDetail(detail);
                break;
            }
        }
        return "/reportList.xhtml?faces-redirect=true";
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