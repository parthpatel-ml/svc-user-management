package com.insta.svc_user_management.model;

import com.insta.svc_user_management.base.IBaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Date;

@Data
@Entity(name = "app_user")
public class User implements IBaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String firstName;

    private String lastName;

    private String number;

    private Date birthDate;

    private String password;

    private String bio;

    private String[] links;

//    private Long profilePicture; // should come from media management service.

    // Follower / Following count update in real time

    /**
     * Database Table for password Management: Store last 5 passwords for any users.
     * Password expiry time
     *
     * Login table: to store use login entry. timestamp.
     * Login session management like cookies or OAuth token.
     *
     * Users should be able to sign up using an email address and a secure password.
     * The system should validate the email format and ensure the password meets security criteria (e.g., minimum length, complexity).
     *
     * Users choose a unique username during registration.
     * The system should check the availability of the username in real-time.
     *
     * Use can log in through userName/Email & Password
     *
     * Users can follow and unfollow other users.
     * The follow/unfollow status is correctly updated in the database.
     * Follow/unfollow actions are reflected in the user’s profile.
     * */

}
