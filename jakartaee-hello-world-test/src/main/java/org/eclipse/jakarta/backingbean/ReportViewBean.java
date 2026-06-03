package org.eclipse.jakarta.backingbean;
import org.eclipse.jakarta.dto.ReportDto;
import org.eclipse.jakarta.infrastracture.repository.ReportRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
public class ReportViewBean {
	
    private ReportDto report;
    
    @Inject
    private ReportRepository reportRepository;
    
    @PostConstruct
    public void init() {
        String idParam = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");
        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            for (ReportDto r : reportRepository.findAll()) {
                if (r.getId().equals(id)) {
                    report = r;
                    break;
                }
            }
        }
    }
    
    public ReportDto getReport() {
        return report;
    }
}