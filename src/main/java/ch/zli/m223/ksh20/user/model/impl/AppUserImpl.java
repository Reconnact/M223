package ch.zli.m223.ksh20.user.model.impl;
import ch.zli.m223.ksh20.user.model.AppUser;
import ch.zli.m223.ksh20.user.model.Booking;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "AppUser")
public class AppUserImpl implements AppUser {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column()
    private String role = "guest";

    /*
    Bro unglaublich - Join mit booking

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Booking> bookings;

    */

    public AppUserImpl(String firstName, String lastName, String email, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        passwordHash = password;
        //TODO: hash it
        this.role = role;
    }

    /** For JPA use only */
    protected AppUserImpl() {}

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setFirstname(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastname(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

}
