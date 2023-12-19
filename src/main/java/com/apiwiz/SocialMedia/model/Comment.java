package com.apiwiz.SocialMedia.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comment_on_post")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long comment_id;

    @Column(name="content")
    String content;
    @Column(name="comment_date")
    @CreationTimestamp
    Date comment_date;

    @ManyToOne
    @JoinColumn
    Post post;

    @ManyToOne
    @JoinColumn
    User user;

}
