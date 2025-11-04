package edu.bootcamp_sb.service_market.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientProfileRequestDto {
    private String profilePicUrl;
}
