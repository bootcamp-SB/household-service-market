package edu.bootcamp_sb.service_market.service.impl;



import edu.bootcamp_sb.service_market.dto.ProviderDto;
import edu.bootcamp_sb.service_market.dto.reponse.CategoryResponseDto;
import edu.bootcamp_sb.service_market.dto.request.CategoryRequestDto;
import edu.bootcamp_sb.service_market.entity.CategoryEntity;
import edu.bootcamp_sb.service_market.entity.ProviderEntity;
import edu.bootcamp_sb.service_market.exception.category_exception.CategaryHasBeenNotFoundException;
import edu.bootcamp_sb.service_market.repository.CategoryRepository;
import edu.bootcamp_sb.service_market.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;

import static edu.bootcamp_sb.service_market.service.impl.ProviderServiceImpl.convertProviderEntityToProviderDto;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    public static CategoryResponseDto convertCategoryEntityToCategoryResponseDto(
            CategoryEntity preConvertDto){

        return CategoryResponseDto.builder()
                .id(preConvertDto.getId())
                .name(preConvertDto.getName())
                .build();
    }

    @Override
    @PreAuthorize("hasAnyRole('admin','provider')")
    public ResponseEntity<Map<String,String>> register(CategoryRequestDto categoryRequestDto) {

        Optional<CategoryEntity> existByName = categoryRepository.findByName(
                                                    categoryRequestDto.getName().toLowerCase());
        if(existByName.isEmpty()){
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setName(categoryRequestDto.getName().toLowerCase());
            CategoryEntity save = categoryRepository.save(categoryEntity);
            return ResponseEntity.ok(Map.of("Successful" ,
                    "Category hasBeen saved"+save.getName()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("Failed", "Category is exsists"));

    }

    @Override
    public ResponseEntity<List<CategoryResponseDto>> getAll() {
        Iterable<CategoryEntity> categoryEntitiesList = categoryRepository.findAll();

        ArrayList<CategoryResponseDto> responsDtoArrayList = new ArrayList<>();

        for(CategoryEntity categoryEntity: categoryEntitiesList){
            responsDtoArrayList.add(convertCategoryEntityToCategoryResponseDto(categoryEntity));
        }

        return ResponseEntity.ok(responsDtoArrayList);
    }

    @Override
    public ResponseEntity<List<ProviderDto>> getProvidersInSameCategory(String categoryName) {
       CategoryEntity categoryEntity = categoryRepository.findByName(
               categoryName.toLowerCase()).orElseThrow(()->
                    new CategaryHasBeenNotFoundException("not exists"));

        ArrayList<ProviderDto> providerDtoArrayList = new ArrayList<>();

        categoryEntity.getProviders().forEach(providerEntity -> {
            providerDtoArrayList.add(convertProviderEntityToProviderDto(providerEntity));
        });

        return ResponseEntity.ok(providerDtoArrayList);
    }

    @Override
    public ResponseEntity<Map<String, String>> deleteCategory(UUID id) {

        CategoryEntity category = categoryRepository.findById(id).orElseThrow(()->
                new CategaryHasBeenNotFoundException("No category")
        );

        Set<ProviderEntity> providers = category.getProviders();

        for(ProviderEntity providerEntity: providers) providerEntity.getCategories().remove(category);

        categoryRepository.deleteById(id);

        return ResponseEntity.ok(Map.of("Deletion", "successfully Deleted:" + category));
    }

    @Override
    public ResponseEntity<Integer> getHowManyProvidersInTheCategory(String categoryName) {
        CategoryEntity categoryEntity = categoryRepository.findByName(categoryName.toLowerCase()).orElseThrow(() ->
                new CategaryHasBeenNotFoundException("there is no category in that name"));

        return ResponseEntity.ok(categoryEntity.getProviders().size());
    }
}
