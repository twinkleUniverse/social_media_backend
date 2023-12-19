package com.apiwiz.SocialMedia.service.impl;

import com.apiwiz.SocialMedia.Enum.PrivacyStatus;
import com.apiwiz.SocialMedia.dto.RequestDTO.post_request_dto.PostRequestDto;
import com.apiwiz.SocialMedia.dto.RequestDTO.post_request_dto.RepostRequestDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.post_response_dto.PostResponseDto;
import com.apiwiz.SocialMedia.exception.postExceptions.PostNotFound;
import com.apiwiz.SocialMedia.exception.userExceptions.UserNotExist;
import com.apiwiz.SocialMedia.model.Post;
import com.apiwiz.SocialMedia.model.User;
import com.apiwiz.SocialMedia.repository.PostRepository;
import com.apiwiz.SocialMedia.repository.UserRepository;
import com.apiwiz.SocialMedia.service.PostService;
import com.apiwiz.SocialMedia.transformer.PostTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    public PostResponseDto post_content(PostRequestDto postRequestDto){
        String username=postRequestDto.getUsername();
        Optional<User> user= userRepository.findByUsername(username);
        Post newPost= PostTransformer.postRequestDtoToPost(postRequestDto);
        newPost.setUser(user.get());
        List<Post> list=user.get().getPostList();
        list.add(newPost);
        user.get().setPostList(list);
        Post savedpost=postRepository.save(newPost);
        return PostTransformer.postToPostResponseDto(savedpost);
    }

    public List<PostResponseDto> allPostList( String username) throws UserNotExist{
        Optional<User>optionalUser=userRepository.findByUsername(username);
        if(!optionalUser.isEmpty()){
           throw new UserNotExist("Invalid user name");
        }

        List<Post>postList=optionalUser.get().getPostList();
        List<PostResponseDto>anslist=new ArrayList<>();
        for(Post post:postList){
            if(post.getPrivacy_status()==PrivacyStatus.PUBLIC){
            PostResponseDto postResponseDto=PostTransformer.postToPostResponseDto(post);
            anslist.add(postResponseDto);}
        }
        return anslist;

    }

    @Override
    public String deletePost(long postId) throws PostNotFound {
        Optional<Post> optionalPost=postRepository.findById(postId);
        if(!optionalPost.isPresent()){
            throw new PostNotFound("Invalid Id!, Post Not Found");
        }
        Post newpost =optionalPost.get();
        User postAdmin=newpost.getUser();
        List<Post> postList = postAdmin.getPostList();
        Iterator<Post> iterator = postList.iterator();
        while (iterator.hasNext()) {
            Post post = iterator.next();
            if (post.getPost_id() == postId) {
                iterator.remove();
            }
        }
        postAdmin.setPostList(postList);
        userRepository.save(postAdmin);
        return "Post Deleted Successfully";

    }

    public PostResponseDto rePost(RepostRequestDto repostRequestDto) throws UserNotExist,PostNotFound{
        Optional<User>optionalUser=userRepository.findByUsername(repostRequestDto.getUsername());
        if(!optionalUser.isPresent()){
            throw new UserNotExist("User not exist");
        }
        Optional<User>optionalCreator=userRepository.findByUsername(repostRequestDto.getCreatorName());
        if(!optionalCreator.isPresent()){
            throw new UserNotExist("Post creator not exist");
        }
        Optional<Post> optionalPost=postRepository.findById(repostRequestDto.getPost_id());
        if(!optionalPost.isPresent()){
            throw new PostNotFound("Invalid Id!, Post Not Found");
        }

        User creator=optionalCreator.get();
        Set<User> followersList=creator.getFollowers();
        Set<User>followingList=creator.getFollowing();
        if(optionalPost.get().getPrivacy_status()== PrivacyStatus.PRIVATE && !(followersList.contains(optionalUser.get())&&
              followingList.contains(optionalUser.get())))throw new PostNotFound("This post is not excessible");
        List<Post>creatorpostList=optionalCreator.get().getPostList();
        List<Post> userPostList=optionalUser.get().getPostList();
        Post repost=PostTransformer.repostRequestDtoToPost(repostRequestDto,optionalUser.get());
        for(Post post:creatorpostList){
            if(post.getPost_id()==repostRequestDto.getPost_id()){
                repost.setContent(post.getContent());
                break;
            }
        }
        userPostList.add(repost);
        optionalUser.get().setPostList(userPostList);
        userRepository.save(optionalUser.get());
        return PostTransformer.postToPostResponseDto(repost);

    }


}
