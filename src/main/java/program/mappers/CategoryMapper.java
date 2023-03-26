package program.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import program.DTOs.CategoryDTOs.CategoryDTO;
import program.DTOs.ProductDTOs.SimpleProductDTO;
import program.entities.Category;
import program.entities.Product;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO CategoryDTOByCategory(Category category);
}
