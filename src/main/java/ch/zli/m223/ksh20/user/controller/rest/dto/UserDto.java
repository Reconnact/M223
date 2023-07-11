package ch.zli.m223.ksh20.user.controller.rest.dto;

import ch.zli.m223.ksh20.user.model.AppUser;

public class UserDto {
    public String firstName, lastName, email, role;

    public UserDto(AppUser appUser){
        firstName = appUser.getFirstName();
        lastName = appUser.getLastName();
        email = appUser.getEmail();
        role = appUser.getRole();
    }

}
