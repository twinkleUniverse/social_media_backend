package com.apiwiz.SocialMedia.repository;

import com.apiwiz.SocialMedia.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    public Optional<Group> findByGroupname(String group_name);
}
