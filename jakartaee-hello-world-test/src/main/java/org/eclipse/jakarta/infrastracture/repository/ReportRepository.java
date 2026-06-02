package org.eclipse.jakarta.infrastracture.repository;
import java.util.List;
import java.util.stream.Collectors;
import org.eclipse.jakarta.dto.ReportDto;
import org.eclipse.jakarta.infrastracture.entity.Report;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
@ApplicationScoped
public class ReportRepository {
    @PersistenceContext(unitName = "jakartaPU")
    private EntityManager em;
    public List<ReportDto> findAll() {
        return em.createQuery("SELECT r FROM Report r", Report.class)
                .getResultList()
                .stream()
                .map(r -> new ReportDto(r.getId(), r.getTitle(), r.getDetail()))
                .collect(Collectors.toList());
    }
    @Transactional
    public void create(ReportDto dto) {
        Report report = new Report();
        report.setTitle(dto.getTitle());
        report.setDetail(dto.getDetail());
        em.persist(report);
    }
    @Transactional
    public void update(ReportDto dto) {
        Report report = em.find(Report.class, dto.getId());
        if (report != null) {
            report.setTitle(dto.getTitle());
            report.setDetail(dto.getDetail());
            em.merge(report);
        }
    }
    @Transactional
    public void delete(Long id) {
        Report report = em.find(Report.class, id);
        if (report != null) {
            em.remove(report);
        }
    }
}