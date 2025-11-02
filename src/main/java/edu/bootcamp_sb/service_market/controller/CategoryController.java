package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.dto.reponse.CategoryResponseDto;
import edu.bootcamp_sb.service_market.dto.request.CategoryRequestDto;
import edu.bootcamp_sb.service_market.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/services")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasAnyRole('admin','provider')")
    public ResponseEntity<CategoryResponseDto>persist(@RequestBody CategoryRequestDto job){
        return categoryService.register(job);
    }

}
