package edu.bootcamp_sb.service_market.dto.request;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ProviderSelectCategoriesDto {
    private UUID providerId;
    private List<UUID> categoriesId;

}
