package program.services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import program.DTOs.CategoryDTOs.CategoryAddDTO;
import program.DTOs.CategoryDTOs.CategoryDTO;
import program.DTOs.CategoryDTOs.CategoryEditDTO;
import program.entities.Category;
import program.interfaces.ICategoryService;
import program.interfaces.IProductService;
import program.mappers.CategoryMapper;
import program.repositories.CategoryRepository;
import program.storage.IStorageService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    private final IStorageService storageService;

    @Override
    public CategoryDTO create(CategoryAddDTO category) {
        var newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setDateCreated(new Date(System.currentTimeMillis()));
        newCategory.setDelete(false);

        var file = storageService.saveMultipartFile(category.getFile());

        newCategory.setImage(file);

        categoryRepository.save(newCategory);

        //var catDTO = categoryMapper.CategoryDTOByCategory(newCategory);

        return null;
    }

    @Override
    public CategoryDTO edit(CategoryEditDTO categoryDTO) {
        Category newCategory = categoryRepository.findById(categoryDTO.getId()).get();
        newCategory.setName(categoryDTO.getName());

        var file = storageService.saveMultipartFile(categoryDTO.getFile());
        storageService.removeFile(newCategory.getImage());

        newCategory.setImage(file);

        categoryRepository.save(newCategory);

        //var catDTO = categoryMapper.CategoryDTOByCategory(newCategory);

        return null;
    }

    @Override
    public void remove(int id) {
        Category category = categoryRepository.findById(id).get();

        if(category!=null){
            storageService.removeFile(category.getImage());
            categoryRepository.delete(category);
        }
    }

    @Override
    public List<CategoryDTO> getAll() {
        var categories = categoryRepository.findAll();

        var categorieDTOs=new ArrayList<CategoryDTO>();

        for(var c:categories){
            var catDTO = categoryMapper.CategoryDTOByCategory(c);
            categorieDTOs.add(catDTO);
        }

        return categorieDTOs;
    }

    @Override
    public CategoryDTO getById(int id) {
        var cat = categoryRepository.getOne(id);

        var catDTO = categoryMapper.CategoryDTOByCategory(cat);
        return catDTO;
    }
}
