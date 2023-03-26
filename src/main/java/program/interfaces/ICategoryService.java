package program.interfaces;

import program.DTOs.CategoryDTOs.CategoryAddDTO;
import program.DTOs.CategoryDTOs.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    CategoryDTO create(CategoryAddDTO category);
    List<CategoryDTO> getAll();
    CategoryDTO getById(int id);
}
