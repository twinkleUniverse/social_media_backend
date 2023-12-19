package com.apiwiz.SocialMedia.dto.RequestDTO.event_request_dto;

import com.apiwiz.SocialMedia.Enum.EventType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventRequestDto {
    String username;
    long post_id;
    EventType event;

}
