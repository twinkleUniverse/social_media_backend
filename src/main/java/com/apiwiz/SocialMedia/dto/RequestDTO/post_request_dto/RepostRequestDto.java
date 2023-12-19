package com.apiwiz.SocialMedia.dto.RequestDTO.post_request_dto;

import com.apiwiz.SocialMedia.Enum.PrivacyStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RepostRequestDto {
    String username;
    String creatorName;
    long post_id;
    String caption;
    PrivacyStatus status;
}
