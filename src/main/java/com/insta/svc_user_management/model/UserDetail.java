package com.insta.svc_user_management.model;

import com.insta.svc_user_management.base.IBaseModel;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.sql.Date;

@Data
@Entity(name = "user_detail")
public class UserDetail implements IBaseModel {
    @Serial
    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "number")
    private String number;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "password")
    private String password;
    @Column(name = "bio")
    private String bio;
    @Column(name = "links")
    private String[] links;

    @OneToOne()
    @JoinColumn(name = "gender")
    private Gender gender;

    @OneToOne()
    @JoinColumn(name = "user_role")
    private UserRole userRole;

    @OneToOne()
    @JoinColumn(name = "user_account_type")
    private UserAccountType userAccountType;

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
