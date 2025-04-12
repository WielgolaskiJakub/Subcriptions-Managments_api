package com.example.subcriptionsmanagments_api.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersResponse {

    private Long id;
    private String username;
    private String email;


    public UsersResponse(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;

    }

}
