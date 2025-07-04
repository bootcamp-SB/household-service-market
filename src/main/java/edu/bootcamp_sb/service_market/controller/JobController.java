package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

}
