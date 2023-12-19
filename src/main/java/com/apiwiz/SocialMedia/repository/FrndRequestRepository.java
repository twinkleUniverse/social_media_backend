package com.apiwiz.SocialMedia.repository;

import com.apiwiz.SocialMedia.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrndRequestRepository extends JpaRepository<FriendRequest,Long> {
}
