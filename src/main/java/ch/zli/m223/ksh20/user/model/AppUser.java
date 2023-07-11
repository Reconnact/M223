package ch.zli.m223.ksh20.user.model;

public interface AppUser {
    Long getId();
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPassword();

    String getRole();
}
