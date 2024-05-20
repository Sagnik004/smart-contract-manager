package in.sagnikchakraborty.entities;

import jakarta.persistence.*;

@Entity(name = "socialLink")
@Table(name = "social_links")
public class SocialLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "link")
    private String link;

    @ManyToOne
    private Contact contact;
}
