package com.apiwiz.SocialMedia.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.*;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="app_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    long user_id;
    @Column(name="username",unique = true,nullable = false)
    String  username;
    @Column(name="email_id",unique = true,nullable = false)
     String email;
    @Column(name="password",unique = true,nullable = false)
     String password;
    @Column(name = "bio")
     String bio;
    @Column(name="profile_picture_url")
     String profilePicture;
    @Column(name="role")
     String role;
    @Column(name = "registration_date")
    @CreationTimestamp
     Date registrationDate;
     @OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
     Set<Group>admin_groupList=new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Post> postList=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Comment>commentList=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Event>eventList = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    List<Message>sendMsgList=new ArrayList<>();
    @OneToMany(mappedBy = "receiver",cascade= CascadeType.ALL)
    List<Message>receivedMsglist=new ArrayList<>();

    @OneToMany(mappedBy = "request_sender", cascade = CascadeType.ALL)
    Set<FriendRequest>friendRequest_send_list=new HashSet<>();

    @OneToMany(mappedBy = "request_receiver", cascade = CascadeType.ALL)
    Set<FriendRequest>friendRequest_received_list=new HashSet<>();


    @ManyToMany
    @JoinTable(
        name = "user_followers",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    Set<User> followers = new HashSet<>();

    @ManyToMany(mappedBy = "followers")
    Set<User> following = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name ="group_id")
    )
    Set<Group> group_list = new HashSet<>();


}
