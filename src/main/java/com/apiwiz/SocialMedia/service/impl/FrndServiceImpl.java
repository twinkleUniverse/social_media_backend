package com.apiwiz.SocialMedia.service.impl;

import com.apiwiz.SocialMedia.Enum.RequestStatus;
import com.apiwiz.SocialMedia.dto.ResponseDTO.frndrequest_response_dto.FrndRequestResponseDto;
import com.apiwiz.SocialMedia.exception.userExceptions.UserNotExist;
import com.apiwiz.SocialMedia.model.FriendRequest;
import com.apiwiz.SocialMedia.model.Post;
import com.apiwiz.SocialMedia.model.User;
import com.apiwiz.SocialMedia.repository.FrndRequestRepository;
import com.apiwiz.SocialMedia.repository.UserRepository;
import com.apiwiz.SocialMedia.service.FrndRequestService;
import com.apiwiz.SocialMedia.transformer.FriendRequestTransformer;
import org.aspectj.weaver.loadtime.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
public class FrndServiceImpl implements FrndRequestService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FrndRequestRepository frndRequestRepository;
    public String sendRequest(String sender,String receiver) throws UserNotExist{
        Optional<User>optionalSender=userRepository.findByUsername(sender);
        Optional<User>optionalReceiver=userRepository.findByUsername(receiver);
        if(!optionalReceiver.isPresent()){
            throw new UserNotExist("Invalid user name");
        }
        if(!optionalSender.isPresent()){
            throw new UserNotExist("User not found");
        }
        FriendRequest newRequest=new FriendRequest();
        newRequest.setRequest_sender(optionalSender.get());
        newRequest.setRequest_receiver(optionalReceiver.get());
        FriendRequest savedRequest=frndRequestRepository.save(newRequest);
        Set<FriendRequest>sendRequestList=optionalSender.get().getFriendRequest_send_list();
        sendRequestList.add(savedRequest);
        optionalSender.get().setFriendRequest_send_list(sendRequestList);
        userRepository.save(optionalSender.get());

        Set<FriendRequest>receivedRequestList=optionalReceiver.get().getFriendRequest_received_list();
        receivedRequestList.add(savedRequest);
        optionalReceiver.get().setFriendRequest_received_list(receivedRequestList);
        userRepository.save(optionalReceiver.get());
        return "Follow Request send to "+receiver+" successfully";
    }

    @Override
    public Set<FrndRequestResponseDto> getAllrequest(String name) {
        User user=userRepository.findByUsername(name).get();
        Set<FriendRequest>friendRequestList=user.getFriendRequest_received_list();
        Set<FrndRequestResponseDto>ansList=new HashSet<>();
        for(FriendRequest item:friendRequestList){
            ansList.add(FriendRequestTransformer.frndRequestToResponseDto(item));
        }
        return ansList;
    }

    public FrndRequestResponseDto accept(String username,String sender_name) throws UserNotExist{
        User user=userRepository.findByUsername(username).get();
        User sender=userRepository.findByUsername(sender_name).get();
        if (user==null) throw  new UserNotExist("User not exist");

        Set<FriendRequest> requestList = user.getFriendRequest_received_list();
        FriendRequest request = requestList.stream()
                .filter(item -> item.getRequest_sender().equals(sender_name))
                .findAny()
                .orElse(null);

        if(request==null) throw new UserNotExist("Request Not Found");
        request.setStatus(RequestStatus.ACCEPTED);

        Iterator<FriendRequest> iterator = requestList.iterator();
        while (iterator.hasNext()) {
            FriendRequest friendRequest = iterator.next();
            if (friendRequest.getRequest_sender().getUsername()==sender_name) {
                iterator.remove();
            }
        }
        user.setFriendRequest_received_list(requestList);
        //set sender as follower of user
        Set<User>followerlist=user.getFollowers();
        followerlist.add(sender);
        user.setFollowers(followerlist);
        userRepository.save(user);

        Set<FriendRequest> sendRequestList= sender.getFriendRequest_send_list();
        Iterator<FriendRequest> iterator1 = sendRequestList.iterator();
        while (iterator1.hasNext()) {
            FriendRequest friendRequest = iterator1.next();
            if (friendRequest.getRequest_receiver().getUsername()==username) {
                iterator1.remove();
            }
        }
        sender.setFriendRequest_send_list(sendRequestList);

        //save user in following list of sender
        Set<User>followinglist=sender.getFollowing();
        followinglist.add(user);
        sender.setFollowing(followinglist);
        userRepository.save(sender);

       frndRequestRepository.delete(request);
       FrndRequestResponseDto requestResponseDto=new FrndRequestResponseDto(sender_name,RequestStatus.ACCEPTED);
        return requestResponseDto ;
    }

    public FrndRequestResponseDto notAccept(String username,String sender_name) throws UserNotExist{
        User user=userRepository.findByUsername(username).get();
        User sender=userRepository.findByUsername(sender_name).get();
        if (user==null) throw  new UserNotExist("User not exist");

        Set<FriendRequest> requestList = user.getFriendRequest_received_list();
        FriendRequest request = requestList.stream()
                .filter(item -> item.getRequest_sender().equals(sender_name))
                .findAny()
                .orElse(null);

        if(request==null) throw new UserNotExist("Request Not Found");
        request.setStatus(RequestStatus.DECLINED);

        Iterator<FriendRequest> iterator = requestList.iterator();
        while (iterator.hasNext()) {
            FriendRequest friendRequest = iterator.next();
            if (friendRequest.getRequest_sender().getUsername()==sender_name) {
                iterator.remove();
            }
        }
        user.setFriendRequest_received_list(requestList);
        userRepository.save(user);
        frndRequestRepository.save(request);
        FrndRequestResponseDto requestResponseDto=new FrndRequestResponseDto(sender_name,RequestStatus.DECLINED);
        return requestResponseDto ;
    }

    public FrndRequestResponseDto followBack(String username,String sender_name) throws UserNotExist{
        User user=userRepository.findByUsername(username).get();
        User sender=userRepository.findByUsername(sender_name).get();
        if (user==null) throw  new UserNotExist("User not exist");

        Set<FriendRequest> requestList = user.getFriendRequest_received_list();
        FriendRequest request = requestList.stream()
                .filter(item -> item.getRequest_sender().equals(sender_name))
                .findAny()
                .orElse(null);

        if(request==null) throw new UserNotExist("Request Not Found");
        request.setStatus(RequestStatus.FOLLOW_BACK);

        Iterator<FriendRequest> iterator = requestList.iterator();
        while (iterator.hasNext()) {
            FriendRequest friendRequest = iterator.next();
            if (friendRequest.getRequest_sender().getUsername()==sender_name) {
                iterator.remove();
            }
        }
        user.setFriendRequest_received_list(requestList);
         //check sender is in following list or not
        Set<User>followinglistUser=user.getFollowing();
        if(!followinglistUser.contains(sender)){
            sendRequest(username,sender_name);
           // return new FrndRequestResponseDto(sender_name,RequestStatus.PENDING);
        }
        //set sender as follower of user
        Set<User>followerlist=user.getFollowers();
        followerlist.add(sender);
        user.setFollowers(followerlist);
        userRepository.save(user);

        Set<FriendRequest> sendRequestList= sender.getFriendRequest_send_list();
        Iterator<FriendRequest> iterator1 = sendRequestList.iterator();
        while (iterator1.hasNext()) {
            FriendRequest friendRequest = iterator1.next();
            if (friendRequest.getRequest_receiver().getUsername()==username) {
                iterator1.remove();
            }
        }
        sender.setFriendRequest_send_list(sendRequestList);

        //save user in following list of sender
        Set<User>followinglist=sender.getFollowing();
        followinglist.add(user);
        sender.setFollowing(followinglist);
        userRepository.save(sender);

        frndRequestRepository.delete(request);
        FrndRequestResponseDto requestResponseDto=new FrndRequestResponseDto(sender_name,RequestStatus.FOLLOW_BACK);
        return requestResponseDto ;
    }

    public Long numberOfFollowers(String username) throws UserNotExist{
        Optional<User>optionalUser=userRepository.findByUsername(username);
        if(!optionalUser.isPresent()){
            throw new UserNotExist("User Not Found");
        }
        User user=optionalUser.get();
        long numbers=user.getFollowers().size();
        return numbers;
    }

    public Long numberOfFollowings(String username) throws UserNotExist{
        Optional<User>optionalUser=userRepository.findByUsername(username);
        if(!optionalUser.isPresent()){
            throw new UserNotExist("User Not Found");
        }
        User user=optionalUser.get();
        long numbers=user.getFollowing().size();
        return numbers;
    }



}
