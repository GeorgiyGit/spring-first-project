package program.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import program.DTOs.ProductDTOs.FullProductDTO;
import program.DTOs.ProductDTOs.ProductCreateDTO;
import program.DTOs.ProductDTOs.ProductEditDTO;
import program.DTOs.ProductDTOs.SimpleProductDTO;
import program.interfaces.IProductService;
import program.services.ProductService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/products")
public class ProductController {
    private final IProductService productService;

    @GetMapping
    public ResponseEntity<List<SimpleProductDTO>> index() {
        var result = productService.getSimple();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FullProductDTO> create(@Valid @ModelAttribute ProductCreateDTO model) {
        var result = productService.create(model);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FullProductDTO> edit(@PathVariable("id") int id,
                                               @Valid @ModelAttribute ProductEditDTO model) {
        var result = productService.edit(id, model);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<FullProductDTO> getProductById(@PathVariable("id") int id) {
        var product = productService.getById(id);
        if(product!=null)
        {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<FullProductDTO> deleteProductById(@PathVariable("id") int id) {
        var product = productService.deleteById(id);
        if(product!=null)
        {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
