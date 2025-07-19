package edu.bootcamp_sb.service_market.dto.reponse;


import edu.bootcamp_sb.service_market.dto.JobDto;
import edu.bootcamp_sb.service_market.dto.ProviderDto;
import lombok.Builder;
import lombok.Data;



import java.util.List;


@Data
@Builder
public class ProviderJobResponseDto {

    private ProviderDto providerDto;

    private List<JobDto> job;
}
