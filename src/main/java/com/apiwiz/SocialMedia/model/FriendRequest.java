package com.apiwiz.SocialMedia.model;

import com.apiwiz.SocialMedia.Enum.RequestStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "friend_request")
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long request_id;
    @Column(name = "request_status")
    @Enumerated(EnumType.STRING)
    RequestStatus status=RequestStatus.PENDING;

    @ManyToOne
    @JoinColumn
    User request_sender;
    @ManyToOne
    @JoinColumn
    User request_receiver;

}
