package at.htl.nvs.persistence;

import at.htl.nvs.entities.Survey;

import javax.ejb.Stateless;

@Stateless
public class SurveyRepository extends Repository<Survey> {

    /*public List<Survey> getOwnedByUser(User user) {
        if(user == null) {
            return new ArrayList<>();
        } else {
            List<Survey> result = em.createNamedQuery("Survey.fromUser", genericClass)
                    .setParameter(1, user.getId())
                    .getResultList();
            return result;
        }
    }*/
}