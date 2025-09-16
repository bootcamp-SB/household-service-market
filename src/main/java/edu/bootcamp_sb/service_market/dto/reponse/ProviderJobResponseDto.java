package edu.bootcamp_sb.service_market.dto.reponse;


import edu.bootcamp_sb.service_market.dto.JobDto;
import edu.bootcamp_sb.service_market.dto.ProviderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProviderJobResponseDto {

    private ProviderDto providerDto;

    private List<JobDto> job;
}
