package edu.bootcamp_sb.service_market.dto.reponse;


import edu.bootcamp_sb.service_market.dto.JobDto;
import lombok.Builder;
import lombok.Data;



import java.util.List;


@Data
@Builder
public class ProviderJobResponseDto {

    private Integer id;

    private String email;

    private String contactNo;

    private Double hourlyRate;

    private String expertise;

    private Boolean isVerified;

    private List<JobDto> job;
}
