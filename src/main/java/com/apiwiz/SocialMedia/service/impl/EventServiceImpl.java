package com.apiwiz.SocialMedia.service.impl;

import com.apiwiz.SocialMedia.Enum.EventType;
import com.apiwiz.SocialMedia.Enum.PrivacyStatus;
import com.apiwiz.SocialMedia.dto.ResponseDTO.post_response_dto.PostResponseDto;
import com.apiwiz.SocialMedia.exception.postExceptions.PostNotFound;
import com.apiwiz.SocialMedia.exception.userExceptions.UserNotExist;
import com.apiwiz.SocialMedia.model.Event;
import com.apiwiz.SocialMedia.model.Post;
import com.apiwiz.SocialMedia.model.User;
import com.apiwiz.SocialMedia.repository.EventRepository;
import com.apiwiz.SocialMedia.repository.PostRepository;
import com.apiwiz.SocialMedia.repository.UserRepository;
import com.apiwiz.SocialMedia.service.EventService;
import com.apiwiz.SocialMedia.transformer.PostTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    EventRepository eventRepository;
    public PostResponseDto likeThePost(long post_id,String likedUsername) throws PostNotFound{
        Optional<Post>optionalPost=postRepository.findById(post_id);
        if(!optionalPost.isPresent()){
            throw new PostNotFound("Post not found");
        }
        Set<User> followersList=optionalPost.get().getUser().getFollowers();
        Set<User>followingList=optionalPost.get().getUser().getFollowing();
        if(optionalPost.get().getPrivacy_status()== PrivacyStatus.PRIVATE && !(followersList.contains(userRepository.findByUsername(likedUsername).get())&&
                followingList.contains(userRepository.findByUsername(likedUsername).get())))throw new PostNotFound("This post is not excessible");
        List<Event>eventList=optionalPost.get().getEventList();
        Event newEvent=new Event();
        newEvent.setEventType(EventType.LIKE);
        newEvent.setPost(optionalPost.get());
        newEvent.setUser(userRepository.findByUsername(likedUsername).get());
        Event saveEvent= eventRepository.save(newEvent);
        eventList.add(saveEvent);
        optionalPost.get().setEventList(eventList);
        Post savePost=postRepository.save(optionalPost.get());
        return PostTransformer.postToPostResponseDto(savePost);
    }

    public String sharePost(long post_id,String sendername,String recievername) throws UserNotExist,PostNotFound {
        Optional<User>optionalSender=userRepository.findByUsername(sendername);
        Optional<User>optionalReceiver=userRepository.findByUsername(recievername);
        Optional<Post>optionalPost=postRepository.findById(post_id);
        if(!optionalSender.isPresent()){
            throw new PostNotFound("Sender not found");
        }
        if(!optionalReceiver.isPresent()){
            throw new PostNotFound("Receiver not found");
        }
        Set<User> followersList=optionalSender.get().getFollowers();
        Set<User>followingList=optionalSender.get().getFollowing();
        if(optionalPost.get().getPrivacy_status()== PrivacyStatus.PRIVATE && !(followersList.contains(optionalReceiver.get())&&
                followingList.contains(optionalReceiver.get())))throw new PostNotFound("This post is not excessible");

        List<Event>eventList=optionalPost.get().getEventList();
        Event newEvent=new Event();
        newEvent.setEventType(EventType.SHARE);
        newEvent.setPost(optionalPost.get());
        newEvent.setUser(optionalSender.get());
        Event saveEvent= eventRepository.save(newEvent);
        eventList.add(saveEvent);
        optionalPost.get().setEventList(eventList);
        Post savePost=postRepository.save(optionalPost.get());
        return "Post shared to"+ recievername +"successfully";
    }

    public String dislike(long postId,String likedUserName) throws PostNotFound {
        Optional<Post> optionalPost=postRepository.findById(postId);
        if(!optionalPost.isPresent()){
            throw new PostNotFound("Invalid Id!, Post Not Found");
        }

        List<Event> eventList = optionalPost.get().getEventList();
        Iterator<Event> iterator = eventList.iterator();
        while (iterator.hasNext()) {
            Event event= iterator.next();
            if (event.getUser().getUsername() == likedUserName) {
                iterator.remove();
            }
        }
        optionalPost.get().setEventList(eventList);
       postRepository.save(optionalPost.get());
        return "Disliked the Post Successfully";
    }

    public int getPostlikes(long post_id) throws PostNotFound{
        Optional<Post>optionalPost=postRepository.findById(post_id);
        if(!optionalPost.isPresent()){
            throw new PostNotFound("Invalid Id!, Post Not Found");
        }
        PostResponseDto postResponseDto=PostTransformer.postToPostResponseDto(optionalPost.get());
        return postResponseDto.getNumber_of_likes();
    }

}
