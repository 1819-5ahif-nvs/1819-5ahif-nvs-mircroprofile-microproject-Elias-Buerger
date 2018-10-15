package at.htl.nvs.entities;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "\"USER\"")
@NamedQuery(name = "User.byRegexName", query = "select user from User user where user.userName like ?1")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_NAME", unique = true)
    @Size(min = 2, max = 20, message = "Username must be between 3 and 20 characters")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Username can only contain letters and numbers")
    private String userName;

    @Column(name = "FIRST_NAME")
    @Size(min = 2, max = 35, message = "First name must be between 2 and 35 characters")
    @Pattern(regexp = "^([A-Za-z]+ ?)+$", message = "First name can only contain letters")
    private String firstName;

    @Column(name = "LAST_NAME")
    @Size(min = 2, max = 35, message = "Last name must be between 2 and 35 characters")
    @Pattern(regexp = "^([A-Za-z]+ ?)+$", message = "Last name can only contain letters")
    private String lastName;

    @Column(name = "EMAIL", unique = true)
    private String email;

    public User() {
    }

    public User(String userName, String firstName, String lastName, String email) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof User && ((User)obj).id == id);
    }
}
