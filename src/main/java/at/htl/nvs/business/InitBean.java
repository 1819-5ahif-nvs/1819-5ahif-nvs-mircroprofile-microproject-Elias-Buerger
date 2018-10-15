package at.htl.nvs.business;

import at.htl.nvs.entities.Survey;
import at.htl.nvs.entities.User;
import at.htl.nvs.persistence.SurveyRepository;
import at.htl.nvs.persistence.UserRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDateTime;

@Singleton
@Startup
public class InitBean {

    @Inject
    private SurveyRepository surveyRepository;

    @Inject
    private UserRepository userRepository;

    @PostConstruct
    private void init() {
        User user1 = new User("user1", "aaa", "AAA", "a.A@aon.at");
        User user2 = new User("user2","bbb", "BBB", "b.B@gmx.at");
        User user3 = new User("user3","ccc", "CCC", "c.C@gmail.com");
        userRepository.create(user1);
        userRepository.create(user2);
        userRepository.create(user3);

        Survey testSurvey = new Survey(
                user1,
                LocalDateTime.now(),
                "Test Survey",
                "A survey with 1 question to find out what your favourite programming languages are");
        Survey testSurvey2 = new Survey(
                user2,
                LocalDateTime.now(),
                "Test Survey for DataTable",
                "");
        Survey testSurvey3 = new Survey(
                user2,
                LocalDateTime.now(),
                "Test Survey for DataTable 2",
                "Another entry for the DataTable");
        surveyRepository.create(testSurvey);
        surveyRepository.create(testSurvey2);
        surveyRepository.create(testSurvey3);
    }
}