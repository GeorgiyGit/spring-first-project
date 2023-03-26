package program.DTOs.AccountDTOs;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class LogInDTO {
    private String email;
    private String password;
}
