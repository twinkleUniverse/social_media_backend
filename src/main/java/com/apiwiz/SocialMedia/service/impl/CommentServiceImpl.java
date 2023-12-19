package com.apiwiz.SocialMedia.service.impl;

import com.apiwiz.SocialMedia.dto.RequestDTO.comment_request_dto.CommentRequestDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.comment_response_dto.CommentsResponseDto;
import com.apiwiz.SocialMedia.dto.ResponseDTO.post_response_dto.PostResponseDto;
import com.apiwiz.SocialMedia.exception.postExceptions.PostNotFound;
import com.apiwiz.SocialMedia.model.Comment;
import com.apiwiz.SocialMedia.model.Post;
import com.apiwiz.SocialMedia.model.User;
import com.apiwiz.SocialMedia.repository.CommentRepository;
import com.apiwiz.SocialMedia.repository.PostRepository;
import com.apiwiz.SocialMedia.repository.UserRepository;
import com.apiwiz.SocialMedia.service.CommentService;
import com.apiwiz.SocialMedia.transformer.CommentTransformer;
import com.apiwiz.SocialMedia.transformer.PostTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    public PostResponseDto commentOnPost(CommentRequestDto commentRequestDto) throws PostNotFound{
        Optional<User> optionalUser=userRepository.findByUsername(commentRequestDto.getCreator());
        List<Post> postList=optionalUser.get().getPostList();

        Post commentPost=findPost(postList,commentRequestDto.getPost_id());
       if(commentPost==null) {
           throw new PostNotFound("Post is not available !");
       }
        Comment comment= CommentTransformer.commentRequestdtoTocomment(commentRequestDto,optionalUser.get(),commentPost);
        Comment saveComment=commentRepository.save(comment);
        List<Comment>commentList=commentPost.getPost_comments_List();
        commentList.add(saveComment);
        commentPost.setPost_comments_List(commentList);
        Post savaPost= postRepository.save(commentPost);
        return PostTransformer.postToPostResponseDto(savaPost);

    }

    public Post findPost(List<Post>postList,long post_id){
        for (Post item:postList){
            if(item.getPost_id()== post_id){
                return item;
            }
        }
        return null;
    }


    public String deleteComment(long comment_id,long post_id) throws PostNotFound{
        Optional<Post>optionalPost=postRepository.findById(post_id);
        if(!optionalPost.isPresent()) {
            throw new PostNotFound("Post is not available !");
        }
        String nameCommenter="";
        List<Comment>commentList=optionalPost.get().getPost_comments_List();
        Iterator<Comment> iterator = commentList.iterator();
        while (iterator.hasNext()) {
            Comment comment = iterator.next();
            if (comment.getComment_id() == comment_id) {
                nameCommenter=comment.getUser().getUsername();
                iterator.remove();
            }
        }
        optionalPost.get().setPost_comments_List(commentList);
        postRepository.save(optionalPost.get());
        return "Comment by"+ nameCommenter +"deleted successfully!";
    }

    public List<CommentsResponseDto> getAllCommentsOnPost(long post_id) throws PostNotFound{
        Optional<Post>optionalPost=postRepository.findById(post_id);
        if(!optionalPost.isPresent()) {
            throw new PostNotFound("Post is not available !");
        }
        List<Comment>commentList=optionalPost.get().getPost_comments_List();
        List<CommentsResponseDto>ansList=new ArrayList<>();
        for (Comment comment:commentList){
            ansList.add(CommentTransformer.commentToCommentReseDto(comment));
        }
        return ansList;
    }
}
