package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.dto.reponse.JobResponseDto;
import edu.bootcamp_sb.service_market.dto.request.JobRequestDto;
import edu.bootcamp_sb.service_market.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/services")
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<JobResponseDto>persist(@RequestBody JobRequestDto job){
        return jobService.register(job);
    }

}
