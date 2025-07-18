package edu.bootcamp_sb.service_market.dto.request;

import edu.bootcamp_sb.service_market.dto.JobDto;
import edu.bootcamp_sb.service_market.dto.ProviderDto;
import lombok.Data;

import java.util.List;

@Data
public class ProviderJobRequestDto {

    private ProviderDto provider;
    private List<JobDto> jobs;
}
