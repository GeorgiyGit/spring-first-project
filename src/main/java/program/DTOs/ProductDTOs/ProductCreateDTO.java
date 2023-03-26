package program.DTOs.ProductDTOs;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductCreateDTO {
    private String name;
    private double price;
    private String description;
    private List<Integer> categories;
    private List<MultipartFile> files;
}