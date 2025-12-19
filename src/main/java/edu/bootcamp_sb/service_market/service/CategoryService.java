package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.ProviderDto;
import edu.bootcamp_sb.service_market.dto.reponse.CategoryResponseDto;
import edu.bootcamp_sb.service_market.dto.request.CategoryRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface CategoryService {

    ResponseEntity<Map<String,String>>register(CategoryRequestDto jobDto);

    ResponseEntity<List<CategoryResponseDto>> getAll();

    ResponseEntity<List<ProviderDto>> getProvidersInSameCategory(String categoryName);

    ResponseEntity<Map<String,String>>deleteCategory(UUID id);

    ResponseEntity<Integer>getHowManyProvidersInTheCategory(String categoryName);


}
