package in.sagnikchakraborty.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {

    @Id
    @Column(name = "id")
    private String userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "about_user", length = 10000)
    private String about;

    @Column(name = "profile_pic", length = 1000)
    private String profilePic;

    @Column(name = "account_enabled")
    private boolean isEnabled = false;

    @Column(name = "email_verified")
    private boolean isEmailVerified = false;

    @Column(name = "phone_verified")
    private boolean isPhoneVerified = false;

    @Column(name = "signup_provider")
    private Providers provider = Providers.SELF;

    @Column(name = "signup_provider_id")
    private String providerId;

    @Column(name = "contacts")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();
}
