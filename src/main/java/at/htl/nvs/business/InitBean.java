package at.htl.nvs.business;

import at.htl.nvs.entities.Survey;
import at.htl.nvs.persistence.SurveyRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.time.LocalDateTime;

@Singleton
@Startup
public class InitBean {

    @EJB
    private SurveyRepository surveyRepository;

    @PostConstruct
    private void init() {
        Survey testSurvey = new Survey(
                LocalDateTime.now(),
                "Test Survey",
                "A survey with 1 question to find out what your favourite programming languages are");
        Survey testSurvey2 = new Survey(
                LocalDateTime.now(),
                "Test Survey for DataTable",
                "");
        Survey testSurvey3 = new Survey(
                LocalDateTime.now(),
                "Test Survey for DataTable 2",
                "Another entry for the DataTable");
        surveyRepository.create(testSurvey);
        surveyRepository.create(testSurvey2);
        surveyRepository.create(testSurvey3);
    }
}