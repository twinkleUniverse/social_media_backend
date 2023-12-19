package com.apiwiz.SocialMedia.dto.RequestDTO.post_request_dto;

import com.apiwiz.SocialMedia.Enum.PrivacyStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequestDto {
    String username;
    String content;
    String caption;
    PrivacyStatus privacySetting;

}
