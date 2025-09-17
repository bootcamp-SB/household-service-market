package edu.bootcamp_sb.service_market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpDto {

    private String to;

    private String otpCode;

    private Integer validMinutes = 5;

    private Instant expireTime;


}
