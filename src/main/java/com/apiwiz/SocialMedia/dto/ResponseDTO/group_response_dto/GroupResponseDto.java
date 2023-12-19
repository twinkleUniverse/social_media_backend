package com.apiwiz.SocialMedia.dto.ResponseDTO.group_response_dto;

import com.apiwiz.SocialMedia.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupResponseDto {
    String group_name;
    String admin_name;
    Set<User>membersSet;

}
