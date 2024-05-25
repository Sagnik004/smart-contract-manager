package in.sagnikchakraborty.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity()
@Table(name = "users")
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
    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;

    @Column(name = "signup_provider_id")
    private String providerId;

    @Column(name = "contacts")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    public User() {
    }

    private User(Builder userBuilder) {
        this.userId = userBuilder.userId;
        this.name = userBuilder.name;
        this.email = userBuilder.email;
        this.password = userBuilder.password;
        this.phoneNumber = userBuilder.phoneNumber;
        this.about = userBuilder.about;
        this.profilePic = userBuilder.profilePic;
        this.isEnabled = userBuilder.isEnabled;
        this.isEmailVerified = userBuilder.isEmailVerified;
        this.isPhoneVerified = userBuilder.isPhoneVerified;
        this.provider = userBuilder.provider;
        this.providerId = userBuilder.providerId;
        this.contacts = userBuilder.contacts;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAbout() {
        return about;
    }
    public void setAbout(String about) {
        this.about = about;
    }

    public String getProfilePic() {
        return profilePic;
    }
    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public boolean isEnabled() {
        return isEnabled;
    }
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }
    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public boolean isPhoneVerified() {
        return isPhoneVerified;
    }
    public void setPhoneVerified(boolean phoneVerified) {
        isPhoneVerified = phoneVerified;
    }

    public Providers getProvider() {
        return provider;
    }
    public void setProvider(Providers provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public List<Contact> getContacts() {
        return contacts;
    }
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", about='" + about + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", isEnabled=" + isEnabled +
                ", isEmailVerified=" + isEmailVerified +
                ", isPhoneVerified=" + isPhoneVerified +
                ", provider=" + provider +
                ", providerId='" + providerId + '\'' +
                ", contacts=" + contacts +
                '}';
    }

    public static class Builder {
        private String userId;
        private String name;
        private String email;
        private String password;
        private String phoneNumber;
        private String about;
        private String profilePic;
        private boolean isEnabled;
        private boolean isEmailVerified;
        private boolean isPhoneVerified;
        private Providers provider = Providers.SELF;
        private String providerId;
        private List<Contact> contacts;

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder about(String about) {
            this.about = about;
            return this;
        }

        public Builder profilePic(String profilePic) {
            this.profilePic = profilePic;
            return this;
        }

        public Builder isEnabled(boolean isEnabled) {
            this.isEnabled = isEnabled;
            return this;
        }

        public Builder isEmailVerified(boolean isEmailVerified) {
            this.isEmailVerified = isEmailVerified;
            return this;
        }

        public Builder isPhoneVerified(boolean isPhoneVerified) {
            this.isPhoneVerified = isPhoneVerified;
            return this;
        }

        public Builder provider(Providers provider) {
            this.provider = provider;
            return this;
        }

        public Builder providerId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder contacts(List<Contact> contacts) {
            this.contacts = contacts;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
