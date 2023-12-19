package com.apiwiz.SocialMedia.model;

import com.apiwiz.SocialMedia.Enum.PrivacyStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    long post_id;

    @Column(name="content",nullable = false)
     String content;          // (text, image, video)

    @Column(name="caption")
    String caption;
    @Column(name="date_post")
    @CreationTimestamp
    Date post_date;

    @Enumerated(EnumType.STRING)
    @Column(name="privacy_status",nullable = false)
    PrivacyStatus privacy_status; // (public, private)

    @ManyToOne
    @JoinColumn
    User user; //(many-to-one relationship with User entity)

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    List<Comment>post_comments_List=new ArrayList<>();

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    List<Event>eventList = new ArrayList<>();

}
