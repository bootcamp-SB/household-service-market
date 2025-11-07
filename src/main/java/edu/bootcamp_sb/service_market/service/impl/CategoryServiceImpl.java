package edu.bootcamp_sb.service_market.service.impl;



import edu.bootcamp_sb.service_market.dto.reponse.CategoryResponseDto;
import edu.bootcamp_sb.service_market.dto.request.CategoryRequestDto;
import edu.bootcamp_sb.service_market.entity.CategoryEntity;
import edu.bootcamp_sb.service_market.repository.CategoryRepository;
import edu.bootcamp_sb.service_market.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    public static CategoryResponseDto convertCategoryEntityToCategoryResponseDto(CategoryEntity preConvertDto){

        return CategoryResponseDto.builder()
                .id(preConvertDto.getId())
                .name(preConvertDto.getName())
                .build();
    }

    @Override
    @PreAuthorize("hasAnyRole('admin','provider')")
    public ResponseEntity<Map<String,String>> register(CategoryRequestDto categoryRequestDto) {

        Optional<CategoryEntity> existByName = categoryRepository.findAllByName(
                                                    categoryRequestDto.getName().toLowerCase());
        if(existByName.isEmpty()){
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setName(categoryRequestDto.getName().toLowerCase());
            CategoryEntity save = categoryRepository.save(categoryEntity);
            return ResponseEntity.ok(Map.of("Successful" , "Category hasBeen saved"+save.getName()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("Failed", "Category is exsists"));

    }
}
