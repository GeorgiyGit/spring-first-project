package program.DTOs.ProductDTOs;

import lombok.Data;
import program.DTOs.CategoryDTOs.CategoryDTO;

import java.util.List;

@Data
public class FullProductDTO {
    private int id;
    private String name;
    private double price;
    private String description;
    private List<CategoryDTO> categories;
    private List<String> images;
}
