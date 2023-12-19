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
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long message_id;

    @Column(name = "content")
    String Content;
    @Column(name="date_of_msg")
    @CreationTimestamp
    Date sent_date;

    @ManyToOne
    @JoinColumn
    User sender;
    @ManyToOne
    @JoinColumn                  //send msg api
    User receiver;               //msg {msg_id,content,date,sender_id,receiver_id}
    @ManyToOne                  //send_user-id=>sender{msg_id}
    @JoinColumn                //receiver_user-id=>receiver{msg_id)
    Group group_msg;

}
