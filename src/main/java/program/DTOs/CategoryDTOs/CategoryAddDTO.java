package program.DTOs.CategoryDTOs;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CategoryAddDTO {
    private String name;
    private MultipartFile file;
}
