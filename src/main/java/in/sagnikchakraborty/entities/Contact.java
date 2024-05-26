package in.sagnikchakraborty.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "contact")
@Table(name = "contacts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Contact {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "picture")
    private String picture;

    @Column(name = "description", length = 10000)
    private String description;

    @Column(name = "favourite")
    private boolean favourite = false;

    @Column(name = "website_link")
    private String websiteLink;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(name = "social_links")
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SocialLink> socialLinks = new ArrayList<>();
}
