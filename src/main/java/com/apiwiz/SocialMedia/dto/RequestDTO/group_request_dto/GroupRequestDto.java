package com.apiwiz.SocialMedia.dto.RequestDTO.group_request_dto;

import com.apiwiz.SocialMedia.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupRequestDto {
    String group_name;
    String admin_name;
    String add_user_name;
}
