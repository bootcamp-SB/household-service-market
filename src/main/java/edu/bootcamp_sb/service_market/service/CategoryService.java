package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.reponse.CategoryResponseDto;
import edu.bootcamp_sb.service_market.dto.request.CategoryRequestDto;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    ResponseEntity<CategoryResponseDto>register(CategoryRequestDto jobDto);
}
