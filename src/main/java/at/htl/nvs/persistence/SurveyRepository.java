package at.htl.nvs.persistence;

import at.htl.nvs.entities.Survey;
import at.htl.nvs.entities.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SurveyRepository extends Repository<Survey> {

    public List<Survey> getOwnedByUser(User user) {
        if(user == null) {
            return new ArrayList<>();
        } else {
            return em.createNamedQuery("Survey.fromUser", genericClass)
                    .setParameter(1, user.getId())
                    .getResultList();
        }
    }
}