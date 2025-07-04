package edu.bootcamp_sb.service_market.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bootcamp_sb.service_market.dto.JobDto;
import edu.bootcamp_sb.service_market.entity.JobEntity;
import edu.bootcamp_sb.service_market.repository.JobRepository;
import edu.bootcamp_sb.service_market.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    private final ObjectMapper mapper;

    @Override
    public ResponseEntity<JobDto> register(JobDto jobDto) {
        JobEntity jobEntity = new JobEntity();
        jobEntity.setName(jobDto.getName());
        jobEntity.setPrice(jobEntity.getPrice());
        jobEntity.setType(jobEntity.getType());
        return ResponseEntity.ok(mapper.convertValue(
                jobRepository.save(jobEntity), JobDto.class));
    }
}
