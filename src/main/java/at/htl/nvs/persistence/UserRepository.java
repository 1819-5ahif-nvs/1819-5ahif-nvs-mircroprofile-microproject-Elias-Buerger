package at.htl.nvs.persistence;

import at.htl.nvs.entities.User;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserRepository extends Repository<User> {

    public UserRepository() {
        super(User.class);
    }

    public List<User> getByRegexUsername(String regex) {
        return em.createNamedQuery("User.byRegexName", genericClass)
                .setParameter(1, regex)
                .getResultList();
    }
}