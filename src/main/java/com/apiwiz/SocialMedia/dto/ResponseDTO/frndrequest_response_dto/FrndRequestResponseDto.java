package com.apiwiz.SocialMedia.dto.ResponseDTO.frndrequest_response_dto;

import com.apiwiz.SocialMedia.Enum.RequestStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FrndRequestResponseDto {
    String sender_name;
    RequestStatus status;
}
