package program.interfaces;

import program.DTOs.CategoryDTOs.CategoryAddDTO;
import program.DTOs.CategoryDTOs.CategoryDTO;
import program.DTOs.CategoryDTOs.CategoryEditDTO;

import java.util.List;

public interface ICategoryService {
    CategoryDTO create(CategoryAddDTO category);
    CategoryDTO edit (CategoryEditDTO categoryDTO);
    void remove (int id);
    List<CategoryDTO> getAll();
    CategoryDTO getById(int id);
}
