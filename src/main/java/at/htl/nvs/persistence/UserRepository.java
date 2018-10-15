package at.htl.nvs.persistence;

import at.htl.nvs.entities.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UserRepository extends Repository<User> {

    public List<User> getByRegexUsername(String regex) {
        return em.createNamedQuery("User.byRegexName", genericClass)
                .setParameter(1, regex)
                .getResultList();
    }
}