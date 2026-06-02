package org.eclipse.jakarta.infrastracture.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String detail;
    public Report() {}
    public Report(Long id, String title, String detail) {
        this.id = id;
        this.title = title;
        this.detail = detail;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }
}