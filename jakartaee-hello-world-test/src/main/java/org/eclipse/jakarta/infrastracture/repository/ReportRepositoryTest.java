package org.eclipse.jakarta.infrastracture.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.eclipse.jakarta.backingbean.ReportListBean;
import org.eclipse.jakarta.dto.ReportDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



class ReportRepositoryTest {

	private ReportRepository repository;
    private ReportDto testReport1;
    private ReportDto testReport2;

    @BeforeEach
    void setUp() {
        repository = new ReportRepository();

        testReport1 = new ReportDto();
        testReport1.setTitle("Test Report 1");
        testReport1.setDetail("This is the first test report");

        testReport2 = new ReportDto();
        testReport2.setTitle("Test Report 2");
        testReport2.setDetail("This is the second test report");

        System.out.println("@BeforeEach: Test setup completed");
    }

    @AfterEach
    void tearDown() {
        repository = null;
        testReport1 = null;
        testReport2 = null;
        System.out.println("@AfterEach: Test cleanup completed");
    }

    @Test
    @DisplayName("Should create a new report with auto-generated ID")
    void testCreate() {
        repository.create(testReport1);

        assertNotNull(testReport1.getId(), "Report ID should be auto-generated");
        assertEquals(1L, testReport1.getId(), "First report should have ID 1");
        assertEquals(1, repository.findAll().size(), "Repository should contain 1 report");
    }
    
    @Test
    void testUpdate() {
    	repository.create(testReport1);
    	
        ReportDto updatedReport = new ReportDto();
        updatedReport.setId(testReport1.getId());
        updatedReport.setTitle("New Title");
        updatedReport.setDetail("New Detail");


        repository.update(updatedReport);

        ReportDto result = repository.findAll().get(0);

        assertEquals("New Title", result.getTitle());
        assertEquals("New Detail", result.getDetail());
        assertEquals(testReport1.getId(), result.getId());
    }
    
    @Test
    void testDelete() {
    	repository.create(testReport1);
    	
    	Long id = testReport1.getId();
    	
    	repository.delete(id);
    	
    	assertTrue(repository.findAll().isEmpty());
    }
    
    @Test
    void testRead() {
        ReportListBean reportList = new ReportListBean();
        
        reportList.setReportRepository(repository);

    	repository.create(testReport1);
    	reportList.init();
    	
    	ArrayList<ReportDto>reports = (ArrayList<ReportDto>) reportList.getReports();
    	
    	assertNotNull(reports);
    	
    }

}
