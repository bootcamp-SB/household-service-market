package edu.bootcamp_sb.service_market.dto.reponse;


import edu.bootcamp_sb.service_market.dto.CategoryDto;
import edu.bootcamp_sb.service_market.dto.ProviderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProviderCategoryResponseDto {

    private ProviderDto providerDto;

    private Set<CategoryDto> categoriesSet;
}
