package com.apiwiz.SocialMedia.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="social_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    long group_id;

    @Column(name="group_name")
    String group_name;


    @JoinColumn
    @ManyToOne
    User admin;
    @ManyToMany(mappedBy = "group_list", cascade = CascadeType.ALL)
    Set<User> members=new HashSet<>();

    @OneToMany(mappedBy = "group_msg", cascade = CascadeType.ALL)
    List<Message>group_messages=new ArrayList<>();

}
