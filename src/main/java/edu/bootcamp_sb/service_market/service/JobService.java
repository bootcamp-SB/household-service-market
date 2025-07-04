package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.JobDto;
import org.springframework.http.ResponseEntity;

public interface JobService {

    ResponseEntity<JobDto>register(JobDto jobDto);
}
