package edu.bootcamp_sb.service_market.service.impl;



import edu.bootcamp_sb.service_market.dto.ProviderDto;
import edu.bootcamp_sb.service_market.dto.reponse.JobResponseDto;
import edu.bootcamp_sb.service_market.dto.request.JobRequestDto;
import edu.bootcamp_sb.service_market.entity.JobEntity;
import edu.bootcamp_sb.service_market.entity.ProviderEntity;
import edu.bootcamp_sb.service_market.exception.providerException.ProviderHasBeenNotFoundException;
import edu.bootcamp_sb.service_market.repository.JobRepository;
import edu.bootcamp_sb.service_market.repository.ProviderRepository;
import edu.bootcamp_sb.service_market.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;





@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;


    private static JobResponseDto convertJobRequestDtoToJobResponse(JobEntity preConvertDto){

        return JobResponseDto.builder()
                .id(preConvertDto.getId())
                .name(preConvertDto.getName())
                .type(preConvertDto.getType())
                .price(preConvertDto.getPrice())
                .build();
    }

    @Override
    @PreAuthorize("hasAnyRole('admin','provider')")
    public ResponseEntity<JobResponseDto> register(JobRequestDto job) {

        JobEntity jobEntity = new JobEntity();
        jobEntity.setName(job.getName());
        jobEntity.setPrice(job.getPrice());
        jobEntity.setType(job.getType());

        JobEntity save = jobRepository.save(jobEntity);

        return ResponseEntity.ok(convertJobRequestDtoToJobResponse(save));
    }
}
