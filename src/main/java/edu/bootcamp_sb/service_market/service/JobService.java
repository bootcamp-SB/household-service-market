package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.JobDto;
import edu.bootcamp_sb.service_market.dto.reponse.JobResponseDto;
import edu.bootcamp_sb.service_market.dto.request.JobRequestDto;
import org.springframework.http.ResponseEntity;

public interface JobService {

    ResponseEntity<JobResponseDto>register(JobRequestDto jobDto);
}
