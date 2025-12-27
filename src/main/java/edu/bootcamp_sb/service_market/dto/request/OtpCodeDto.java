package edu.bootcamp_sb.service_market.dto.request;

import java.time.Instant;

public record OtpCodeDto(String code , Instant expireTime) {
}
