package edu.bootcamp_sb.service_market.service.impl;



import edu.bootcamp_sb.service_market.dto.reponse.CategoryResponseDto;
import edu.bootcamp_sb.service_market.dto.request.CategoryRequestDto;
import edu.bootcamp_sb.service_market.entity.CategoryEntity;
import edu.bootcamp_sb.service_market.repository.CategoryRepository;
import edu.bootcamp_sb.service_market.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;





@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    private static CategoryResponseDto convertJobRequestDtoToJobResponse(CategoryEntity preConvertDto){

        return CategoryResponseDto.builder()
                .id(preConvertDto.getId())
                .name(preConvertDto.getName())
                .build();
    }

    @Override
    @PreAuthorize("hasAnyRole('admin','provider')")
    public ResponseEntity<CategoryResponseDto> register(CategoryRequestDto categoryRequestDto) {

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryRequestDto.getName());
        CategoryEntity save = categoryRepository.save(categoryEntity);

        return ResponseEntity.ok(convertJobRequestDtoToJobResponse(save));
    }
}
