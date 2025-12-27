package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.dto.ProviderDto;
import edu.bootcamp_sb.service_market.dto.reponse.CategoryResponseDto;
import edu.bootcamp_sb.service_market.dto.request.CategoryRequestDto;
import edu.bootcamp_sb.service_market.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasAnyRole('admin','provider')")
    public ResponseEntity<Map<String,String>>persist(@RequestBody CategoryRequestDto job){
        return categoryService.register(job);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDto>>getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/providers-of-certain-category")
    public ResponseEntity<List<ProviderDto>>getProvidersInThatcategory(@RequestParam String name){
        return categoryService.getProvidersInSameCategory(name);
    }

    @DeleteMapping("/by-id")
    public ResponseEntity<Map<String,String>>deleteCategory(@RequestParam UUID id){
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/provider-count")
    public ResponseEntity<Integer>getProviderCountOfTheCategory(@RequestParam String name){
        return categoryService.getHowManyProvidersInTheCategory(name);
    }
}
