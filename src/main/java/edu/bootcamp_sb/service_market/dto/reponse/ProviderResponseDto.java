package edu.bootcamp_sb.service_market.dto.reponse;


import edu.bootcamp_sb.service_market.dto.BookingDto;
import edu.bootcamp_sb.service_market.dto.ProviderDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProviderResponseDto {
    private ProviderDto providerDto;

    private List<BookingResponseDto> booking;

    private List<CategoryResponseDto> categories;

    private List<ServiceGigResponseDto> gigs;

    private List<ReviewResponseDto> reviews;
}
