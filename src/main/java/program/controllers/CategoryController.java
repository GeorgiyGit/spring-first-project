package program.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import program.DTOs.CategoryDTOs.CategoryAddDTO;
import program.DTOs.CategoryDTOs.CategoryDTO;
import program.DTOs.ProductDTOs.FullProductDTO;
import program.DTOs.ProductDTOs.ProductCreateDTO;
import program.DTOs.ProductDTOs.ProductEditDTO;
import program.DTOs.ProductDTOs.SimpleProductDTO;
import program.interfaces.ICategoryService;
import program.services.CategoryService;
import program.services.ProductService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/categories")
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> index() {
        var result = categoryService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.ALL_VALUE)
    public ResponseEntity<CategoryDTO> create(@Valid @ModelAttribute CategoryAddDTO model) {
        var result = categoryService.create(model);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getProductById(@PathVariable("id") int id) {
        var product = categoryService.getById(id);
        if(product!=null)
        {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
