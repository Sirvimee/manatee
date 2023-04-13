package ee.cyber.manatee.api;


import ee.cyber.manatee.dto.ApplicationStateDto;
import ee.cyber.manatee.dto.InterviewDto;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import ee.cyber.manatee.dto.ApplicationDto;
import ee.cyber.manatee.dto.CandidateDto;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class ApplicationApiTests {

    @Autowired
    private ApplicationApi applicationApi;


    @Test
    public void submitApplicationWithValidData() {
        val draftCandidate = CandidateDto
                .builder().firstName("Mari").lastName("Maasikas").build();
        val draftApplication = ApplicationDto
                .builder().candidate(draftCandidate).build();

        val response = applicationApi.addApplication(draftApplication);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        val application = response.getBody();
        assertNotNull(application);
        assertNotNull(application.getId());
        assertNotNull(application.getApplicationState());
        assertNotNull(application.getUpdatedOn());

        assertEquals(draftApplication.getCandidate().getFirstName(),
                     application.getCandidate().getFirstName());
        assertEquals(draftApplication.getCandidate().getLastName(),
                     application.getCandidate().getLastName());
    }

    /*
    Before test change in application.properties
    spring.jpa.hibernate.ddl-auto=update
    to spring.jpa.hibernate.ddl-auto=create-drop and restart 'Manatee'
    */

    @Test
    public void submitApplicationWithValidDataAndAddInterview() {
        // Create a draft candidate and application
        val draftCandidate = CandidateDto
                .builder().firstName("Mari").lastName("Maasikas").build();

        val draftApplication = ApplicationDto
                .builder().candidate(draftCandidate).build();

        // Submit the draft application
        val response = applicationApi.addApplication(draftApplication);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Retrieve the newly created application
        val createdApplication = response.getBody();
        assertNotNull(createdApplication);

        // Add interview information to the application
        val interview = InterviewDto.builder()
                .interviewer("John Doe")
                .interviewTime(OffsetDateTime.now().plusDays(3).plusHours(1))
                .build();

        createdApplication.setInterview(interview);

        // Update the application with the interview information
        val updateResponse = applicationApi.scheduleInterview(createdApplication.getId(), createdApplication);
        assertEquals(HttpStatus.CREATED, updateResponse.getStatusCode());

        // Retrieve the updated application
        val updatedApplication = updateResponse.getBody();
        assertNotNull(updatedApplication);

        // Check if the interview information is added to the application and is application state changed to INTERVIEW
        val interviewFromApplication = updatedApplication.getInterview();
        assertNotNull(interviewFromApplication);
        assertEquals(interview.getInterviewer(), interviewFromApplication.getInterviewer());
        assertEquals(interview.getInterviewTime(), interviewFromApplication.getInterviewTime());
        assertEquals(ApplicationStateDto.INTERVIEW, updatedApplication.getApplicationState());

    }


}
