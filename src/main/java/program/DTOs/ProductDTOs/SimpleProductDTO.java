package program.DTOs.ProductDTOs;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import program.DTOs.CategoryDTOs.CategoryDTO;

import java.util.List;


@Data
public class SimpleProductDTO {
    private int id;
    private String name;
    private double price;
    private List<CategoryDTO> categories;
    private List<String> files;
}
